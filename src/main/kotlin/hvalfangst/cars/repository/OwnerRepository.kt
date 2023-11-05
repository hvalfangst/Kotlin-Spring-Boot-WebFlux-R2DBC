package hvalfangst.cars.repository

import hvalfangst.cars.model.Owners
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository : R2dbcRepository<Owners, Long>
