package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.dto.VehicleDto
import cz.edu.upce.fei.nnpro.mapper.VehicleMapper
import cz.edu.upce.fei.nnpro.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*
import org.springframework.security.access.prepost.PreAuthorize

@RestController
@RequestMapping("/api/vehicle")
class VehicleController(
    @Autowired val vehicleService: VehicleService,
    @Autowired val vehicleMapper: VehicleMapper
) {
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(vehicleService.getById(id).let(vehicleMapper::toDto))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Vehicle with id $id not found!"))
        }
    }

    @GetMapping("/available-vehicles")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun listAvailableByCompanyIds(@RequestParam companyIds: List<Long>) =
        vehicleService.getAvailableVehicleForCompanies(companyIds).map(vehicleMapper::toDto)

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun listAll() = vehicleService.getAll().map(vehicleMapper::toDto)

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(@RequestBody vehicleDto: VehicleDto) =
        vehicleService.save(vehicleDto.let(vehicleMapper::toModel))
            .let(vehicleMapper::toDto)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun deleteById(@PathVariable id: Long): ResponseEntity<ResponseDto> {
        return try {
            vehicleService.delete(id)
            ResponseEntity.ok().body(ResponseDto("Vehicle deleted!"))
        } catch (e: EmptyResultDataAccessException) {
            ResponseEntity.status(542).body(ResponseDto("Vehicle with id $id not found!"))
        }
    }
}
