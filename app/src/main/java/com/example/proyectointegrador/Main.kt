package com.example.proyectointegrador

import java.util.Calendar
import java.util.GregorianCalendar

fun main() {

    val fecha = Calendar.getInstance()
    fecha.set(Calendar.HOUR, 9)  //Sets the hour to be 10
    fecha.set(Calendar.AM_PM, Calendar.AM) //Sets AM
    // println(fecha)
    //  fecha.set(2022, 5, 31, 9, 0, 0)
    println("Hora de: " + (fecha.getTime()))
    val car = Vehicle("AA111AA", VehicleType.Car, fecha, "DISCOUNT_CARD_001")
    val moto = Vehicle("B222BBB", VehicleType.MotorBike, fecha)
    val minibus = Vehicle("CC333CC", VehicleType.MiniBus, fecha)
    val bus = Vehicle("DD444DD", VehicleType.Bus, fecha, "DISCOUNT_CARD_002")
    val parking = Parking(mutableSetOf(car, moto, minibus))
    parking.addVehicle(bus)
    parking.removeVehicle(bus.plate)
    /*for (i in 1..20) {
        var car2 =
            Vehicle("AA111AE" + i, VehicleType.Car, Calendar.getInstance(), "DISCOUNT_CARD_001")
        parking.addVehicle(car2)
    }*/
    //println(isCar2Inserted)
    parking.removeVehicle(moto.plate)
/*    println(parking.vehicles.contains(car))
    println(parking.vehicles.contains(moto))
    println(parking.vehicles.contains(minibus))
    println(parking.vehicles.contains(bus))*/

    /*val parkingSpace = ParkingSpace(bus)
    parkingSpace.checkOutVehicle(bus.plate, parking)*/
    //parking.addVehicle(bus)
    //  println("parkedTime: " + parkingSpace.parkedTime)
    parking.showEarnings()
    parking.listVehicles()
}