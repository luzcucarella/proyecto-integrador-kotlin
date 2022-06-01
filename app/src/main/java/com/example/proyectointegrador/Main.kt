package com.example.proyectointegrador

import java.util.Calendar
import java.util.GregorianCalendar

fun main() {

    val date = Calendar.getInstance()
    date.set(Calendar.HOUR, 6)  //Sets the hour to be 10
    date.set(Calendar.AM_PM, Calendar.AM) //Sets AM
    val car = Vehicle("AA111AA", VehicleType.Car, date, "DISCOUNT_CARD_001")
    val moto = Vehicle("B222BBB", VehicleType.MotorBike, date)
    val minibus = Vehicle("CC333CC", VehicleType.MiniBus, date)
    val bus = Vehicle("DD444DD", VehicleType.Bus, date, "DISCOUNT_CARD_002")
    val parking = Parking(mutableSetOf(car, moto, minibus))
    parking.addVehicle(bus)
    parking.removeVehicle(bus.plate)
    parking.removeVehicle(moto.plate)
    parking.showEarnings()
    parking.listVehicles()
    /*for (i in 1..20) {
        var car2 =
            Vehicle("AA111AE" + i, VehicleType.Car, Calendar.getInstance(), "DISCOUNT_CARD_001")
        parking.addVehicle(car2)
    }*/
    //println(isCar2Inserted)

/*    println(parking.vehicles.contains(car))
    println(parking.vehicles.contains(moto))
    println(parking.vehicles.contains(minibus))
    println(parking.vehicles.contains(bus))*/

    /*val parkingSpace = ParkingSpace(bus)
    parkingSpace.checkOutVehicle(bus.plate, parking)*/
    //parking.addVehicle(bus)
    //  println("parkedTime: " + parkingSpace.parkedTime)

}