package hvalfangst.cars.repository

import hvalfangst.cars.model.Cars
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: R2dbcRepository<Cars, Long>

