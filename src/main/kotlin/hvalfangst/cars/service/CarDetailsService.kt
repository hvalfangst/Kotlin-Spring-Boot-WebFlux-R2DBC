package hvalfangst.cars.service

import hvalfangst.cars.model.*
import hvalfangst.cars.model.aggregates.CombinedFluxResult
import hvalfangst.cars.model.aggregates.CombinedMonoResult
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
    fun getCarDetails(carId: Long): Mono<CarDetails> {
        val carsMono: Mono<Cars> = carRepository.findById(carId)
        val ownersMono: Mono<Owners> = carsMono.flatMap { ownerRepository.findById(it.id!!) }
        val enginesMono: Mono<Engines> = carsMono.flatMap { engineRepository.findByCarId(it.id!!) }
        val tiresFlux: Flux<Tires> = tireRepository.findByCarId(carId)
        val insurancesFlux: Flux<Insurance> = insuranceRepository.findByCarId(carId)
        val repairsFlux: Flux<Repairs> = repairRepository.findByCarId(carId)

        // Combine data from Mono sources (cars, owners, and engines) into a CombinedMonoResult
        val combinedMono: Mono<CombinedMonoResult> = carsMono.zipWith(ownersMono).zipWith(enginesMono).map {
            CombinedMonoResult(it.t1.t1, it.t1.t2, it.t2)
        }

        // Combine data from Flux sources (tires, insurances, and repairs) into a CombinedFluxResult
        val combinedFlux: Mono<CombinedFluxResult> =
            tiresFlux.collectList().zipWith(insurancesFlux.collectList()).zipWith(repairsFlux.collectList()).map {
                CombinedFluxResult(it.t1.t1, it.t1.t2, it.t2)
            }

        // Finally, merge the CombinedMonoResult and CombinedFluxResult to produce a Mono<CarDetails>
        val carDetailsMono: Mono<CarDetails> = Mono.zip(
            combinedMono,
            combinedFlux
        ) { monoResult, fluxResult ->
            CarDetails(
                monoResult.cars,
                monoResult.owners,
                fluxResult.tires,
                fluxResult.insurances,
                fluxResult.repairs,
                monoResult.engines
            )
        }

        return carDetailsMono
    }
}
