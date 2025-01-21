package com.micronaut.kotlin_impl.dto

import com.micronaut.kotlin_impl.repository.Car
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate
import java.util.UUID

@Serdeable
data class CarResponse(
    var id: UUID,
    var carName: String,
    var model: String,
    var make: LocalDate,
    var price: Double,
    var stock: Int
) {
    fun carToCarResponse(car: Car): CarResponse {
        return CarResponse(
            car.id!!, car.carName, car.model,
            car.make, car.price, car.stock
        )
    }
}
