package hardware.repositories

import hardware.domain.Computer
//import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ComputerRepository : CrudRepository<Computer, Long> {
    fun findByComputerName(computerName: String): Optional<Computer>
}