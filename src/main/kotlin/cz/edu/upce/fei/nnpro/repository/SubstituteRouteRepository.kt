package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.SubstituteRoute
import org.springframework.data.jpa.repository.JpaRepository

interface SubstituteRouteRepository : JpaRepository<SubstituteRoute, Long> {
    fun getByConcernedTrainRoute_Id(id: Long): SubstituteRoute?
}
