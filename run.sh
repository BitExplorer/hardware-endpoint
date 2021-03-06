#!/usr/bin/env bash

ENV=$1
APP=hardware-endpoint

if [ $# -ne 1 ]; then
  echo "Usage: $0 <prod|local|stage|perf>"
  exit 1
fi

if [ "$ENV" = "prod" ] || [ "$ENV" = "local" ] || [ "$ENV" = "stage" ] || [ "$ENV" = "perf" ]; then
  echo "${ENV}"
else
  echo "Usage: $0 <prod|local|stage|perf>"
  exit 2
fi

# "$OSTYPE" == "darwin"*
if [ "$OS" = "Linux Mint" ] || [ "$OS" = "Ubuntu" ] || [ "$OS" = "Raspbian GNU/Linux" ]; then
  HOST_IP=$(hostname -I | awk '{print $1}')
elif [ "$OS" = "Arch Linux" ]; then
  HOST_IP=192.168.100.207
elif [ "$OS" = "openSUSE Tumbleweed" ]; then
  HOST_IP=192.168.100.193
elif [ "$OS" = "Solus" ]; then
  HOST_IP=192.168.100.118
elif [ "$OS" = "Fedora" ]; then
  HOST_IP=192.168.100.130
elif [ "$OS" = "Darwin" ]; then
  HOST_IP=$(ipconfig getifaddr en0)
  # echo "lsof -nP | grep LISTEN"
elif [ "$OS" = "void" ]; then
  HOST_IP=127.0.0.1
elif [ "$OS" = "Gentoo" ]; then
  HOST_IP=$(hostname -i | awk '{print $1}')
else
  echo "$OS is not yet implemented."
  exit 1
fi

export HOST_IP
export CURRENT_UID="$(id -u)"
export CURRENT_GID="$(id -g)"

package=hardware
mkdir -p "src/main/kotlin/$package"
mkdir -p "src/test/unit/groovy/$package"
mkdir -p "src/test/integration/groovy/$package"
mkdir -p "src/test/functional/groovy/$package"
mkdir -p "src/test/performance/groovy/$package"
mkdir -p 'database-data'
mkdir -p 'logs'
mkdir -p 'ssl'

touch env.secrets

chmod +x gradle/wrapper/gradle-wrapper.jar

if [ "$ENV" = "prod" ]; then
  # if ! ./gradlew clean build functionalTest; then
  if ! ./gradlew clean build; then
    echo "gradle build failed."
    exit 1
  fi
else
  if ! ./gradlew clean build -x test; then
    echo "gradle build failed."
    exit 1
  fi
fi

INFLUX_CONTAINER=$(docker ps -a -f 'name=influxdb' --format "{{.ID}}") 2> /dev/null

if [ -n "${INFLUX_CONTAINER}" ]; then
  echo docker rm -f "${INFLUX_CONTAINER}"
  docker rm -f "${INFLUX_CONTAINER}"  2> /dev/null
fi

echo docker run -it -h influxdb-server --net=raspi-bridge -p 8086:8086 --rm --name influxdb-server -d influxdb
echo curl -i -X POST http://localhost:8086/query -u "henninb:monday1" --data-urlencode "q=CREATE DATABASE metrics"

echo curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=metrics" -u "henninb:monday1" --data-urlencode "q=SELECT \"value\" FROM \"stuff\""

echo curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=metrics" -u "henninb:monday1" --data-urlencode "q=SHOW SERIES ON metrics"

echo curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=metrics" -u "henninb:monday1" --data-urlencode "q=SHOW measurements on metrics"

if [ -x "$(command -v docker-compose)" ]; then
  if ! docker-compose -f docker-compose.yml -f "docker-compose-${ENV}.yml" config > docker-compose-run.yml; then
    echo "docker-compose config failed."
    exit 1
  fi

  if ! docker-compose -f docker-compose-run.yml build; then
    echo "docker-compose build failed."
    exit 1
  fi

  if ! docker-compose -f docker-compose-run.yml up; then
    echo "docker-compose up failed."
    exit 1
  fi
  rm docker-compose-run.yml
else
  set -a
  # shellcheck disable=SC1091
  source env.prod
  # shellcheck disable=SC1091
  source env.secrets
  set +a

  ./gradlew clean build bootRun
fi

exit 0
