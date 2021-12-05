package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.SubstituteRoute
import cz.edu.upce.fei.nnpro.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SubstituteRouteRepository : JpaRepository<SubstituteRoute, Long> {
    fun getByConcernedTrainRoute_Id(id: Long): SubstituteRoute?


    @Query(
        value = "select * from substitute_route where substitute_route.train_route_id in (:trainRouteIds);",
        nativeQuery = true
    )
    fun findByTrainRouteIds(@Param("trainRouteIds") trainRouteIds: List<Long>): List<SubstituteRoute>
}
