package hvalfangst.cars.controller

import hvalfangst.cars.repository.OwnerRepository
import hvalfangst.cars.model.Owner
import hvalfangst.cars.model.requests.UpsertOwnerRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/owners")
class OwnerController(private val ownerRepository: OwnerRepository) {

    @GetMapping
    fun findAll(): Flux<Owner> {
        return ownerRepository.findAllOwners()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UpsertOwnerRequest): Mono<Void> {
        return ownerRepository.createOwner(
            request.ownerName,
            request.contactInfo,
            request.dateOfBirth
        )
    }
}