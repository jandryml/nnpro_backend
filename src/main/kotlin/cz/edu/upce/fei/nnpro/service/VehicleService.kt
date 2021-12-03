package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.dto.SubstituteRouteDto
import cz.edu.upce.fei.nnpro.model.Vehicle
import cz.edu.upce.fei.nnpro.repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VehicleService(
    @Autowired private val vehicleRepository: VehicleRepository,
    @Autowired private val trainRouteService: TrainRouteService
) {
    fun save(vehicle: Vehicle) = vehicleRepository.save(vehicle)

    fun getById(id: Long) = vehicleRepository.getById(id)

    fun getAll(): List<Vehicle> = vehicleRepository.findAll()

    fun getAvailableVehicleForCompanies(companyIds: List<Long>) =
        vehicleRepository.findVehicleByCompanyIds(companyIds)

    fun getBySubRouteId(id: Long) = vehicleRepository.getBySubstituteRouteId(id)

    fun delete(id: Long) = vehicleRepository.deleteById(id)

    fun removeReferencesToSubRoute(id: Long) =
        getBySubRouteId(id).forEach {
            save(it.apply { substituteRoute = null })
        }

    fun validateBusesToTrainRouteCapacity(trainRouteId: Long, vehicleIds: List<Long>): Boolean {
        return trainRouteService.getById(trainRouteId)?.let {
            val vehiclesCapacity = vehicleIds.map { vehicleRepository.getById(it) }.sumOf { it.capacity }
            it.capacity <= vehiclesCapacity
        } ?: false
    }

    fun validateVehicleAvailability(subRoute: SubstituteRouteDto): Boolean {
        return subRoute.vehicleIds.map { getById(it) }.none { it.substituteRoute != null && it.substituteRoute?.id != subRoute.id }
    }
}
