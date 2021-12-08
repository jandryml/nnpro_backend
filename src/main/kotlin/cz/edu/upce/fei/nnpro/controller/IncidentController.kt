package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.Incident
import cz.edu.upce.fei.nnpro.service.IncidentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/incident")
class IncidentController(
    @Autowired val incidentService: IncidentService
) {
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(incidentService.getById(id))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Incident with id $id not found!"))
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun listAll(): List<Incident> = incidentService.getAll()

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    fun save(@RequestBody incident: Incident) =
        incidentService.save(incident) ?: ResponseEntity.status(542)
            .body(ResponseDto("Incident must have defined rail!"))


    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun delete(@RequestBody incident: Incident) = incidentService.delete(incident)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun deleteById(@PathVariable id: Long) = incidentService.deleteById(id)

}
