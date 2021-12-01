package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.dto.TransportCompanyDto
import cz.edu.upce.fei.nnpro.mapper.TransportCompanyMapper
import cz.edu.upce.fei.nnpro.service.TransportCompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transport-company")
class TransportCompanyController(
    @Autowired val transportCompanyService: TransportCompanyService,
    @Autowired val transportCompanyMapper: TransportCompanyMapper

) {
    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(transportCompanyService.getById(id).let(transportCompanyMapper::toDto))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Transport company with id $id not found!"))
        }
    }

    @GetMapping
    fun listAll() = transportCompanyService.getAll().map(transportCompanyMapper::toDto)

    @PostMapping
    fun save(@RequestBody transportCompanyDto: TransportCompanyDto) =
        transportCompanyService.save(transportCompanyDto.let(transportCompanyMapper::toModel))
            .let(transportCompanyMapper::toDto)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<ResponseDto> {
        return try {
            transportCompanyService.delete(id)
            ResponseEntity.ok().body(ResponseDto("Transport company deleted!"))
        } catch (e: EmptyResultDataAccessException) {
            ResponseEntity.status(542).body(ResponseDto("Transport company with id $id not found!"))
        }
    }
}
