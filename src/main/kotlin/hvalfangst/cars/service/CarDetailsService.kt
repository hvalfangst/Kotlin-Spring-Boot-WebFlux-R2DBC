package hvalfangst.cars.service

import hvalfangst.cars.model.*
import hvalfangst.cars.repository.CarRepository
import hvalfangst.cars.repository.OwnerRepository
import hvalfangst.cars.repository.EngineRepository
import hvalfangst.cars.repository.TireRepository
import hvalfangst.cars.repository.InsuranceRepository
import hvalfangst.cars.repository.RepairRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CarDetailsService(
    private val carRepository: CarRepository,
    private val ownerRepository: OwnerRepository,
    private val engineRepository: EngineRepository,
    private val tireRepository: TireRepository,
    private val insuranceRepository: InsuranceRepository,
    private val repairRepository: RepairRepository
) {
    fun getCarDetails(carId: Int): Mono<CarDetails> {
        val carMono: Mono<Car> = carRepository.getCarById(carId)
        val ownerMono: Mono<Owner> = carMono.flatMap { ownerRepository.getOwnerById(it.ownerId) }
        val engineMono: Mono<Engine> = carMono.flatMap { engineRepository.getEngineByCarId(it.id) }
        val tiresFlux: Flux<Tire> = tireRepository.getTiresByCarId(carId)
        val insurancesFlux: Flux<Insurance> = insuranceRepository.getInsurancesByCarId(carId)
        val repairsFlux: Flux<Repair> = repairRepository.getRepairsByCarId(carId)

        val combinedMono: Mono<CombinedMonoResult> = carMono.zipWith(ownerMono).zipWith(engineMono).map {
            CombinedMonoResult(it.t1.t1, it.t1.t2, it.t2)
        }

        val combinedFlux: Mono<CombinedFluxResult> = tiresFlux.collectList().zipWith(insurancesFlux.collectList()).zipWith(repairsFlux.collectList()).map {
            CombinedFluxResult(it.t1.t1, it.t1.t2, it.t2)
        }

        val carDetailsMono: Mono<CarDetails> = Mono.zip(
            combinedMono,
            combinedFlux
        ) { monoResult, fluxResult ->
            CarDetails(monoResult.car, monoResult.owner, fluxResult.tires, fluxResult.insurances, fluxResult.repairs, monoResult.engine)
        }

        return carDetailsMono
    }
}

data class CombinedMonoResult(
    val car: Car,
    val owner: Owner,
    val engine: Engine,
)

data class CombinedFluxResult(
    val tires: List<Tire>,
    val insurances: List<Insurance>,
    val  repairs: List<Repair>
)
