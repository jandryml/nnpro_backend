package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.TransportCompany
import cz.edu.upce.fei.nnpro.repository.TransportCompanyRepository
import org.springframework.stereotype.Service

@Service
class TransportCompanyService(
    private val transportCompanyRepository: TransportCompanyRepository
) {
    fun save(transportCompany: TransportCompany) = transportCompanyRepository.save(transportCompany)

    fun getById(id: Long) = transportCompanyRepository.getById(id)

    fun getAll(): List<TransportCompany> = transportCompanyRepository.findAll()

    fun delete(id: Long) = transportCompanyRepository.deleteById(id)
}
