package hvalfangst.cars.controller

import hvalfangst.cars.model.Engines
import hvalfangst.cars.repository.EngineRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/engines")
class EngineController(private val engineRepository: EngineRepository) {

    @GetMapping
    fun findAll(): Flux<Engines> {
        return engineRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Engines> {
        return engineRepository.findByCarId(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Engines): Mono<Engines> {
        return engineRepository.save(request)
    }
}