package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.Chauffeur
import cz.edu.upce.fei.nnpro.model.SubstituteRoute
import org.springframework.data.jpa.repository.JpaRepository

interface ChauffeurRepository : JpaRepository<Chauffeur, Long>
