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


//    @Unroll
//    def "test validation invalid #invalidField has error #expectedError"() {
//        given:
//        Computer computer = new ComputerBuilder()
//                .accountType(accountType)
//                .moniker(moniker)
//                .accountNameOwner(accountNameOwner)
//                .activeStatus(activeStatus)
//                .totals(totals)
//                .totalsBalanced(totalsBalanced)
//                .build()
//
//        when:
//        Set<ConstraintViolation<Computer>> violations = validator.validate(computer)
//
//        then:
//        violations.size() == errorCount
//        violations.message.contains(expectedError)
//        violations.iterator().next().getInvalidValue() == computer.getProperties()[invalidField]
//
//        where:
//        invalidField       | accountId | accountType        | accountNameOwner   | moniker | activeStatus | totals                 | totalsBalanced         | expectedError                              | errorCount
//        'accountNameOwner' | 123L      | AccountType.Debit  | 'blah_chase_brian' | '0000'  | true         | new BigDecimal("0.00") | new BigDecimal("0.00") | 'must be alpha separated by an underscore' | 1
//    }

}
