package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface VehicleRepository : JpaRepository<Vehicle, Long> {
    @Query(
        value = "select * from vehicle where company_id in (:companyIds) and substitute_route_id is null;",
        nativeQuery = true
    )
    fun findVehicleByCompanyIds(@Param("companyIds") companyIds: List<Long>): List<Vehicle>

    fun getBySubstituteRouteId(id: Long): List<Vehicle>
}
