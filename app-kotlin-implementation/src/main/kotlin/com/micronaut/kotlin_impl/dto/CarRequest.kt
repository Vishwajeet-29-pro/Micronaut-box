package com.micronaut.kotlin_impl.dto

import com.micronaut.kotlin_impl.repository.Car
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class CarRequest(
    var carName: String,
    var model: String,
    var make: LocalDate,
    var price: Double,
    var stock: Int,
) {
    fun carRequestToCar(carRequest: CarRequest): Car {
        return Car(
            null,
            carRequest.carName,
            carRequest.model,
            carRequest.make,
            carRequest.price,
            carRequest.stock
        )
    }
}
