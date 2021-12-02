package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ChauffeurDto
import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.mapper.ChauffeurMapper
import cz.edu.upce.fei.nnpro.service.ChauffeurService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chauffeur")
class ChauffeurController(
    @Autowired val chauffeurService: ChauffeurService,
    @Autowired val chauffeurMapper: ChauffeurMapper

) {
    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(chauffeurService.getById(id).let(chauffeurMapper::toDto))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Chauffeur with id $id not found!"))
        }
    }

    @GetMapping
    fun listAll() = chauffeurService.getAll().map(chauffeurMapper::toDto)

    @PostMapping
    fun save(@RequestBody chauffeurDto: ChauffeurDto) =
        chauffeurService.save(chauffeurDto.let(chauffeurMapper::toModel))
            .let(chauffeurMapper::toDto)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<ResponseDto> {
        return try {
            chauffeurService.delete(id)
            ResponseEntity.ok().body(ResponseDto("Chauffeur deleted!"))
        } catch (e: EmptyResultDataAccessException) {
            ResponseEntity.status(542).body(ResponseDto("Chauffeur with id $id not found!"))
        }
    }
}
