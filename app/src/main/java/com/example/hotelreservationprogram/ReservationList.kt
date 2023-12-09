package com.example.hotelreservationprogram

object ReservationList {
    val reservationList = arrayListOf<ReservationListData>()

    fun displayReservationList() {
        println("호텔 예약자 목록입니다.\n")
        reservationList.forEachIndexed { index, it ->
            println("${index + 1}. 사용자: ${it.name}, 방번호: ${it.roomNumber}, 체크인: ${it.checkInDate}, 체크아웃: ${it.checkOutDate}")
        }
    }


    fun displaySortedReservationList() {
        println("호텔 예약자 (정렬)목록입니다.\n")
        reservationList.forEachIndexed { index, it ->
            println("${index + 1}. 사용자: ${it.name}, 방번호: ${it.roomNumber}, 체크인: ${it.checkInDate}, 체크아웃: ${it.checkOutDate}")
            }
        }
    }
}