package com.example.hotelreservationprogram

import java.time.LocalDate


data class ReservationListData(val name: String, val roomNumber: Int,var checkInDate: LocalDate,  var checkOutDate: LocalDate, val randomMoney:Int ,val reservationFee: Int)
