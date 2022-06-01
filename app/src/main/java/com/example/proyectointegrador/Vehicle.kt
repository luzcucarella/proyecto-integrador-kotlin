package com.example.proyectointegrador

import java.util.Calendar

// vehicleType is a val because it won't change
data class Vehicle(
    val plate: String,
    val vehicleType: VehicleType,
    var checkInTime: Calendar,
    var discountCard: String? = null
) {

    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int = this.plate.hashCode()
}