package hvalfangst.cars.repository

import hvalfangst.cars.model.Car
import hvalfangst.cars.model.PrayerRecord
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface CarRepository: R2dbcRepository<PrayerRecord, Int> {

    @Query("SELECT * FROM cars")
    fun findAllCars(): Flux<Car>

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(@Param("id") id: Int): Mono<Car>

    @Modifying
    @Query("INSERT INTO cars (make, model, year, vin, color, purchase_date, owner_id) " +
            "VALUES (:make, :model, :year, :vin, :color, :purchase_date, :owner_id)")
    fun createCar(
        @Param("make") make: String,
        @Param("model") model: String,
        @Param("year") year: Int,
        @Param("vin") vin: String,
        @Param("color") color: String,
        @Param("purchase_date") purchaseDate: LocalDate,
        @Param("owner_id") ownerId: Int
    ): Mono<Void>

}

