package hvalfangst.cars.repository

import hvalfangst.cars.model.Tires
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TireRepository : R2dbcRepository<Tires, Long> {
    @Query("SELECT * FROM tires WHERE car_id = :car_id")
    fun findByCarId(@Param("car_id") carId: Long): Flux<Tires>
}
