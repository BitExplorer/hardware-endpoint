package hardware.domain

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import hardware.helpers.ComputerBuilder
import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.math.RoundingMode
import java.sql.Date
import java.sql.Timestamp

//@MicronautTest
//@MicronautTest(propertySources = "application-unit.yml")
//@Property(name = "micronaut.server.port", value = "-1")
class ComputerSpec extends Specification {

    ValidatorFactory validatorFactory
    Validator validator
    private ObjectMapper mapper = new ObjectMapper()

    def setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def cleanup() {
        validatorFactory.close()
    }

    def "test validation valid computer"() {
        given:
        Computer computer = ComputerBuilder.builder().build()

        when:
        Set<ConstraintViolation<Computer>> violations = validator.validate(computer)

        then:
        violations.isEmpty()
        0 * _
    }
}
