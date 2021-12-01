package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.Chauffeur
import cz.edu.upce.fei.nnpro.repository.ChauffeurRepository
import org.springframework.stereotype.Service

@Service
class ChauffeurService(
    private val chauffeurRepository: ChauffeurRepository
) {
    fun save(chauffeur: Chauffeur) = chauffeurRepository.save(chauffeur)

    fun getById(id: Long) = chauffeurRepository.getById(id)

    fun getAll(): List<Chauffeur> = chauffeurRepository.findAll()

    fun delete(chauffeur: Chauffeur) = chauffeurRepository.delete(chauffeur)
}
