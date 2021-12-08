package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.SubstituteRouteSection
import cz.edu.upce.fei.nnpro.service.SubstituteRouteSectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/substitute-route-section")
class SubstituteRouteSectionController(
    @Autowired val substituteRouteSectionService: SubstituteRouteSectionService
) {
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(substituteRouteSectionService.getById(id))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Substitute route with id $id not found!"))
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun listAll(): List<SubstituteRouteSection> = substituteRouteSectionService.getAll()

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(@RequestBody substituteRouteSection: SubstituteRouteSection) =
        substituteRouteSectionService.save(substituteRouteSection)

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun delete(@RequestBody substituteRouteSection: SubstituteRouteSection) =
        substituteRouteSectionService.delete(substituteRouteSection)
}
