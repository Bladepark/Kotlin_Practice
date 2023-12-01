package com.example.hotelreservationprogram

import java.lang.NumberFormatException
import java.lang.System.exit
import kotlin.system.exitProcess

/*1. 사용자가 호텔 예약을 할 수 있는 메뉴를 표시하세요. (번호는 1~6번까지 만들어봐요.)
2. 메뉴에서 4번을 누르면 호텔 예약 프로그램을 종료할 수 있어요
3. 예약 플로우는 성함을 입력받고 방 번호를 입력받고 체크인 날짜를 입력받고 체크아웃 날짜를 입력받아요
4. 1번을 눌러 방 예약을 받을 수 있도록 구현해 봐요
5. 방 번호는 100~999호실까지 존재해요
6. 체크인 날짜는 지금 날짜와 비교해서 이전날짜는 입력받을 수 없고 체크아웃 날짜는 체크인 날짜보다 이전이거나 같을 수는 없어요
7. 입력이 완료되면 임의의 금액을 지급해 주고 랜덤으로 호텔 예약비로 빠져나가도록 구현해 봐요

lv2
1. 메뉴에서 2번을 눌러 호텔 예약자 목록을 보여줘요. (예시. 1. 사용자: ㅇㅇㅇㅇ, 방 번호: ㅇㅇㅇ호, 체크인: 2023-07-21. 체크아웃: 2023-08-01)
2. 메뉴에서 3번을 눌러 호텔 예약자 목록을 정렬 기능을 사용하여 체크인 날짜순으로 오름차순으로 정렬해 봐요
3. 예약 플로우를 수정해 봐요. 해당 체크인 체크아웃 날짜에 선택한 방 번호를 예약 가능한지 불가능한지 판단하게 변경해 봐요. 예약이 불가능하면 체크인, 체크아웃 날짜를 변경해서 다시 검사해 보는 플로우를 만들어봐요.
[1. 사용자: ㅇㅇㅇㅇ, 방 번호: ㅇㅇㅇ호, 체크인: 2023-07-21. 체크아웃: 2023-08-01]
*/


fun main() {
    var menuNumber: Int
    var reserveRoom = ReserveRoom()
    println("[메뉴]")
    println("1. 방예약 2. 예약목록 출력 3. 예약목록 (정렬) 출력 4. 시스템 종료 5. 금액 입금-출금 내역 목록 출력 6. 예약 변경/취소")
    menuNumber = getMenuNumber()
    while (true) {
        when(menuNumber) {
            1 -> reserveRoom.reserveRoom()
            2 -> continue
            3 -> continue
            4 -> {println("시스템을 종료합니다.");exitProcess(0)}
            5 -> continue
            6 -> continue
        }
    }
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