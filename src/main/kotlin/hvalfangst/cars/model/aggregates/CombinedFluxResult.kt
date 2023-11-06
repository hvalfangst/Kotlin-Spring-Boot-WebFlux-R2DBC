package hvalfangst.cars.model.aggregates

import hvalfangst.cars.model.Insurance
import hvalfangst.cars.model.Repairs
import hvalfangst.cars.model.Tires

data class CombinedFluxResult(
    val tires: List<Tires>,
    val insurances: List<Insurance>,
    val  repairs: List<Repairs>
)
