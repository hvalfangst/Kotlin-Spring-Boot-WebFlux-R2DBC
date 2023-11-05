package hvalfangst.cars.controller

import hvalfangst.cars.model.Engine
import hvalfangst.cars.model.requests.UpsertEngineRequest
import hvalfangst.cars.repository.EngineRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/engines")
class EngineController(private val engineRepository: EngineRepository) {

    @GetMapping
    fun findAll(): Flux<Engine> {
        return engineRepository.findAllEngines()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UpsertEngineRequest): Mono<Void> {
        return engineRepository.createEngine(
            request.carId,
            request.type,
            request.displacement,
            request.horsepower,
            request.manufacturingDate
        )
    }
}