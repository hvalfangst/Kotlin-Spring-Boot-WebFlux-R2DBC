package hvalfangst.cars.controller

import hvalfangst.cars.model.Cars
import hvalfangst.cars.repository.CarRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/cars")
class CarController(private val carRepository: CarRepository) {

    @GetMapping
    fun findAll(): Flux<Cars> {
        return carRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Cars> {
        return carRepository.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Cars): Mono<Cars> {
        return carRepository.save(request)
    }

}
