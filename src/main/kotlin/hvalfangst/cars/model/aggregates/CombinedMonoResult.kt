package hvalfangst.cars.model.aggregates

import hvalfangst.cars.model.Cars
import hvalfangst.cars.model.Engines
import hvalfangst.cars.model.Owners

data class CombinedMonoResult(
    val cars: Cars,
    val owners: Owners,
    val engines: Engines,
)