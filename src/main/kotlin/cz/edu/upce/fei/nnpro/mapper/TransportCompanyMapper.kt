package cz.edu.upce.fei.nnpro.mapper

import cz.edu.upce.fei.nnpro.dto.TransportCompanyDto
import cz.edu.upce.fei.nnpro.model.TransportCompany
import org.springframework.stereotype.Service

@Service
class TransportCompanyMapper {
    fun toModel(transportCompanyDto: TransportCompanyDto)= transportCompanyDto.run {
        TransportCompany(id, name)
    }

    fun toDto(transportCompany: TransportCompany) = transportCompany.run {
        TransportCompanyDto(id, name)
    }
}