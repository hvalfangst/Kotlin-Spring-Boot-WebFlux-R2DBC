package hvalfangst.cars.controller

import hvalfangst.cars.model.Tire
import hvalfangst.cars.model.requests.UpsertTireRequest
import hvalfangst.cars.repository.TireRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/tires")
class TireController(private val tireRepository: TireRepository) {

    @GetMapping
    fun findAll(): Flux<Tire> {
        return tireRepository.findAllTires()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UpsertTireRequest): Mono<Void> {
        return tireRepository.createTire(
            request.carId,
            request.brand,
            request.size,
            request.manufacturingDate
        )
    }
}