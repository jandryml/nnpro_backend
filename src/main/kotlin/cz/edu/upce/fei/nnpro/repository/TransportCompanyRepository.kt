package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.Chauffeur
import cz.edu.upce.fei.nnpro.model.SubstituteRoute
import cz.edu.upce.fei.nnpro.model.TransportCompany
import org.springframework.data.jpa.repository.JpaRepository

interface TransportCompanyRepository : JpaRepository<TransportCompany, Long>
