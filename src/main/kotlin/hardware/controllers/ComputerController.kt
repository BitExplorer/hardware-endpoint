package hardware.controllers

import hardware.domain.Computer
import hardware.services.ComputerService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Controller("/computer")
class ComputerController(@Inject val computerService: ComputerService) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Get("/select/all")
    fun selectAllComputers(): MutableHttpResponse<MutableIterable<Computer>> {
        val computers = computerService.findAllComputers()
        return HttpResponse.ok(computers)
    }
}