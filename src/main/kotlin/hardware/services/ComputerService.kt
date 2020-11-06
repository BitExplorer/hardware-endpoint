package hardware.services

import hardware.domain.Computer
import hardware.repositories.ComputerRepository
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComputerService(@Inject val categoryRepository: ComputerRepository) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun insertComputer(computer: Computer): Boolean {
        categoryRepository.save(computer)
        return true
    }

    fun findByComputerName(computerName: String): Optional<Computer> {
        val categoryOptional: Optional<Computer> = categoryRepository.findByComputerName(computerName)
        if (categoryOptional.isPresent) {
            return categoryOptional
        }
        return Optional.empty()
    }

    fun findAllComputers(): MutableIterable<Computer> {
        return categoryRepository.findAll()
    }
}