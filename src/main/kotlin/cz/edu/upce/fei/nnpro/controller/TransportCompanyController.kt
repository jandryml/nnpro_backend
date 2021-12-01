package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.TransportCompany
import cz.edu.upce.fei.nnpro.service.TransportCompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transport-company")
class TransportCompanyController(
    @Autowired val transportCompanyService: TransportCompanyService
) {
    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(transportCompanyService.getById(id))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Transport company with id $id not found!"))
        }
    }

    @GetMapping
    fun listAll(): List<TransportCompany> = transportCompanyService.getAll()

    @PostMapping
    fun save(@RequestBody transportCompany: TransportCompany) = transportCompanyService.save(transportCompany)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = transportCompanyService.delete(id)
}
