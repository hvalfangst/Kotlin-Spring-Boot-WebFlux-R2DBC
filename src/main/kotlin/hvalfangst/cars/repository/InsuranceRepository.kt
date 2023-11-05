package hvalfangst.cars.repository

import hvalfangst.cars.model.Insurance
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface InsuranceRepository : R2dbcRepository<Insurance, Long> {
    @Query("SELECT * FROM insurance WHERE car_id = :car_id")
    fun findByCarId(@Param("car_id") carId: Long): Flux<Insurance>
}
