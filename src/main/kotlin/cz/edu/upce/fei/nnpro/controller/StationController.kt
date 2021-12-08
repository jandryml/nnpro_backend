package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.Station
import cz.edu.upce.fei.nnpro.service.StationService
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/station")
class StationController(
	val stationService: StationService
) {
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
	fun detail(@PathVariable id: Long): ResponseEntity<Any> {
		return try {
			ResponseEntity.ok(stationService.getById(id))
		} catch (e: JpaObjectRetrievalFailureException) {
			ResponseEntity.status(542).body(ResponseDto("Station with id $id not found!"))
		}
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
	fun listAll(): List<Station> = stationService.getAll()

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	fun save(@RequestBody station: Station) = stationService.save(station)

	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	fun delete(@RequestBody station: Station) = stationService.delete(station)

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	fun deleteById(@PathVariable id: Long) = stationService.deleteById(id)

}
