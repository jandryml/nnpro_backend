package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.Chauffeur
import cz.edu.upce.fei.nnpro.service.ChauffeurService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chauffeur")
class ChauffeurController(
    @Autowired val chauffeurService: ChauffeurService
) {
    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(chauffeurService.getById(id))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Chauffeur with id $id not found!"))
        }
    }

    @GetMapping
    fun listAll(): List<Chauffeur> = chauffeurService.getAll()

    @PostMapping
    fun save(@RequestBody chauffeur: Chauffeur) = chauffeurService.save(chauffeur)

    @DeleteMapping
    fun delete(@RequestBody chauffeur: Chauffeur) = chauffeurService.delete(chauffeur)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = chauffeurService.delete(chauffeurService.getById(id))
}
