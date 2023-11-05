package hvalfangst.cars.controller

import hvalfangst.cars.model.Car
import hvalfangst.cars.model.requests.UpsertCarRequest
import hvalfangst.cars.repository.CarRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/cars")
class CarController(private val carRepository: CarRepository) {

    @GetMapping
    fun findAll(): Flux<Car> {
        return carRepository.findAllCars()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UpsertCarRequest): Mono<Void> {
        return carRepository.createCar(
            request.make,
            request.model,
            request.year,
            request.vin,
            request.color,
            request.purchaseDate,
            request.ownerId
        )
    }

}
