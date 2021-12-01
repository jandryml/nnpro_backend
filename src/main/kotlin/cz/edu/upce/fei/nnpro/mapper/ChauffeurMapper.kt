package cz.edu.upce.fei.nnpro.mapper

import cz.edu.upce.fei.nnpro.dto.ChauffeurDto
import cz.edu.upce.fei.nnpro.model.Chauffeur
import cz.edu.upce.fei.nnpro.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChauffeurMapper(
    @Autowired val vehicleService: VehicleService
) {
    fun toModel(chauffeurDto: ChauffeurDto) = chauffeurDto.run {
        Chauffeur(id, firstname,lastname, drivingLicense, drivingExperience, phoneNumber, vehicleService.getById(vehicleId))
    }

    fun toDto(chauffeur: Chauffeur) = chauffeur.run {
        ChauffeurDto(id, firstname, lastname, drivingLicense, drivingExperience, phoneNumber, vehicle!!.id)
    }
}