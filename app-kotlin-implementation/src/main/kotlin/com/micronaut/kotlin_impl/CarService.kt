package com.micronaut.kotlin_impl

import com.micronaut.kotlin_impl.dto.CarRequest
import com.micronaut.kotlin_impl.dto.CarResponse
import java.util.UUID

interface CarService {
    fun addCarDetails(carRequest: CarRequest): CarResponse
    fun fetchAllCars(): List<CarResponse>
    fun fetchCarDetailsById(id: UUID): CarResponse
    fun updateCarDetailsById(id: UUID, carRequest: CarRequest): CarResponse
    fun deleteCarDetailsById(id: UUID)
}