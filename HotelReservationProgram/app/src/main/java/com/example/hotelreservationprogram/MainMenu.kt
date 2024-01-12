package com.example.hotelreservationprogram

import java.lang.NumberFormatException
import kotlin.system.exitProcess
//### **Lv.1(저) - 필수**
//
//1. 사용자가 호텔 예약을 할 수 있는 메뉴를 표시하세요. (번호는 1~6번까지 만들어봐요.)
//2. 사용자에게 랜덤하게 10000~50000사이의 금액을 넣어줘요.
//3. 메뉴에서 4번을 누르면 호텔 예약 프로그램을 종료할 수 있고, 다른번호를 누르면 메뉴의 이름을 출력해요

class MainMenu {
    fun mainMenu() {
        while (true) {
            println("[메뉴]")
            println("1. 방예약 2. 예약목록 출력 3. 예약목록 (정렬) 출력 4. 시스템 종료 5. 금액 입금-출금 내역 목록 출력 6. 예약 변경/취소")
            when(val menuNumber = getMenuNumber()) {
                1 -> {
                    var reserveRoom = ReserveRoom()
                    reserveRoom.reserveRoom()
                }
                2 -> ReservationList.displayReservationList()
                3 -> ReservationList.displaySortedReservationList()
                4 -> {println("시스템을 종료합니다.");exitProcess(0)}
                5 -> {
                    // 이름 입력 받고
                    // 1. 초기 금액으로 0000원 입금되었습니다.
                    // 2. 예약금으로 0000원 출금되었습니다.

                }
                6 -> continue
            }
        }
    }
}
fun getMenuNumber() : Int{
    while (true) {
        try {
            var menuNumber = readLine()!!.toInt()
            if (menuNumber in 1..6) return menuNumber
            else println("입력 오류! 1~6까지의 숫자 중에 입력해주세요. \n 1. 방예약 2. 예약목록 출력 3. 예약목록 (정렬) 출력 4. 시스템 종료 5. 금액 입금-출금 내역 목록 출력 6. 예약 변경/취소")
        } catch (e: NumberFormatException) {
            println("입력 오류! 숫자를 입력해주세요.")
        }
    }
}