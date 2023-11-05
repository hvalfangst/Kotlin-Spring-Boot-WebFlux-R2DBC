package hvalfangst.cars.controller

import hvalfangst.cars.model.Repair
import hvalfangst.cars.model.requests.UpsertRepairRequest
import hvalfangst.cars.repository.RepairRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/repairs")
class RepairController(private val repairRepository: RepairRepository) {

    @GetMapping
    fun findAll(): Flux<Repair> {
        return repairRepository.findAllRepairs()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UpsertRepairRequest): Mono<Void> {
        return repairRepository.createRepair(
            request.carId,
            request.repairType,
            request.repairDate,
            request.cost
        )
    }
}
