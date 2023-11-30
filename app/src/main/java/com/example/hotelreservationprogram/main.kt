package com.example.hotelreservationprogram

import java.lang.NumberFormatException

fun main() {
    var menuNumber: Int
    println("[메뉴]")
    println("1. 방예약 2. 예약목록 출력 3. 예약목록 (정렬) 출력 4. 시스템 종료 5. 금액 입금-출금 내역 목록 출력 6. 예약 변경/취소")
    menuNumber = getMenuNumber()
    if (menuNumber == 4) return

}

fun getMenuNumber() : Int{
    while (true) {
        try {
            var menuNumber = readLine()!!.toInt()
            if (menuNumber in 1..6) return menuNumber
            else println("입력 오류! 1~6까지의 숫자 중에 입력해주세요. \n 1. 방예약 2. 예약목록 출력 3. 예약목록 (정렬) 출력 4. 시스템 종료 5. 금액 입금-출금 내역 목록 출력 6. 예약 변경/취소")
        } catch (e:NumberFormatException) {
            println("입력 오류! 숫자를 입력해주세요.")
        }
    }
}