package hvalfangst.cars.controller

import hvalfangst.cars.model.Tires
import hvalfangst.cars.repository.TireRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/tires")
class TireController(private val tireRepository: TireRepository) {

    @GetMapping
    fun findAll(): Flux<Tires> {
        return tireRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Tires> {
        return tireRepository.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Tires): Mono<Tires> {
        return tireRepository.save(request)
    }
}