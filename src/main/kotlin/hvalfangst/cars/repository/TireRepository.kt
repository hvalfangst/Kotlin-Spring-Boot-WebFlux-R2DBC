package hvalfangst.cars.repository

import hvalfangst.cars.model.Tire
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface TireRepository : R2dbcRepository<Tire, Int> {

    @Query("SELECT * FROM tires")
    fun findAllTires(): Flux<Tire>

    @Query("SELECT * FROM tires WHERE car_id = :car_id")
    fun getTiresByCarId(@Param("car_id") carId: Int): Flux<Tire>

    @Modifying
    @Query("INSERT INTO tires (car_id, brand, size, manufacturing_date) " +
            "VALUES (:car_id, :brand, :size, :manufacturing_date)")
    fun createTire(
        @Param("car_id") carId: Int,
        @Param("brand") brand: String,
        @Param("size") size: String,
        @Param("manufacturing_date") manufacturingDate: LocalDate
    ): Mono<Void>
}
