package cz.edu.upce.fei.nnpro.mapper

import cz.edu.upce.fei.nnpro.dto.VehicleDto
import cz.edu.upce.fei.nnpro.model.Vehicle
import cz.edu.upce.fei.nnpro.service.TransportCompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VehicleMapper(
    @Autowired val companyService: TransportCompanyService
) {
    fun toModel(vehicleDto: VehicleDto) = vehicleDto.run {
        Vehicle(id, name, capacity, parameters, companyService.getById(companyId))
    }

    fun toDto(vehicle: Vehicle) = vehicle.run {
        VehicleDto(id, name, capacity, parameters, company!!.id)
    }
}