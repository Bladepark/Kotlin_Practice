package com.example.hotelreservationprogram


import java.time.LocalDate
import java.time.format.DateTimeFormatter

/*3. 예약 플로우는 성함을 입력받고 방 번호를 입력받고 체크인 날짜를 입력받고 체크아웃 날짜를 입력받아요
4. 1번을 눌러 방 예약을 받을 수 있도록 구현해 봐요
5. 방 번호는 100~999호실까지 존재해요
6. 체크인 날짜는 지금 날짜와 비교해서 이전날짜는 입력받을 수 없고 체크아웃 날짜는 체크인 날짜보다 이전이거나 같을 수는 없어요
7. 입력이 완료되면 임의의 금액을 지급해 주고 랜덤으로 호텔 예약비로 빠져나가도록 구현해 봐요*/



class ReserveRoom {
    fun reserveRoom(){
        println("예약자분의 성함을 입력해주세요")
        var name = isValidName()
        println("예약할 방 번호를 입력해주세요")
        var roomNumber = getRoomNumber()
        println("체크인 날짜를 입력해주세요. 표기형식  -> YYYYMMDD 예시 -> 20231201")
        var checkInDateInt = getCheckInDate()
        println("체크아웃 날짜를 입력해주세요. 표기형식  -> YYYYMMDD 예시 -> 20231201")
        var checkOutDateInt = getCheckOutDate(checkInDateInt)
        val randomMoney = (10000..50000).random()
        val reservationFee = (10000..25000).random()
        var checkInDate = LocalDate.parse(checkInDateInt.toString(), DateTimeFormatter.BASIC_ISO_DATE)
        checkInDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var checkOutDate = LocalDate.parse(checkOutDateInt.toString(), DateTimeFormatter.BASIC_ISO_DATE)
        checkOutDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val reservationList = ReservationListData(name,roomNumber,checkInDate,checkOutDate,randomMoney, reservationFee)
        ReservationList.reservationList.add(reservationList)
        println("호텔 예약이 완료되었습니다.")
    }
}

fun isValidName() :String{
    while (true) {
        try {
            var name = readln()
            if (name.matches(Regex(".*\\d+.*"))) throw InvalidUserNameException("이름에 숫자가 포함되어 있습니다. 숫자를 빼고 다시 입력해주세요.")
            return name
        } catch (e: InvalidUserNameException) {
            println(e.message)
        } catch (e: Exception) {
            println("잘못된 형식의 이름입니다. 다시 입력해주세요.")
        }
    }
}

fun getRoomNumber() : Int {
    while (true) {
        try {
            var roomNumber = readln().toInt()
            if (roomNumber<100||roomNumber>999) println("올바르지 않은 방 번호입니다. 방번호는 100~999 영역 이내입니다.")
            else return roomNumber
        } catch (e: Exception) {
            println("입력 오류! 100~999 이내 숫자로 입력해주세요!")
        }
    }
}

fun getCheckInDate() : Int{
    val localDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    val currentDate = localDate.format(formatter).toInt()

    while (true) {
        try {
            val checkInDate = readln().toInt()
            if(checkInDate>20250101) println("체크인은 2025년 1월 1일 이후의 날을 선택할 수 없습니다.\n체크인 날짜를 입력해주세요. 표기형식  -> YYYYMMDD 예시 -> 20231201")
            else if (checkInDate < currentDate) println("체크인은 지난 날을 선택할 수 없습니다.\n체크인 날짜를 입력해주세요. 표기형식  -> YYYYMMDD 예시 -> 20231201")
            else return checkInDate
        } catch (e: Exception) {
            println("입력 오류! 표기형식을 확인해주세요.")
        }
    }
}

fun getCheckOutDate(checkInDate:Int) : Int{
    while (true) {
        try {
            var checkOutDate = readln().toInt()
            if(checkOutDate>99999999) println("잘못된 범위의 날짜를 선택할 수 없습니다.\n체크아웃 날짜를 입력해주세요. 표기형식  -> YYYYMMDD 예시 -> 20231201")
            else if (checkOutDate <= checkInDate) println("체크아웃 날짜는 체크인 날짜보다 이전이거나 같을 수 없습니다.\n체크아웃 날짜를 입력해주세요. 표기형식  -> YYYYMMDD 예시 -> 20231201")
            else return checkOutDate
        } catch (e: Exception) {
            println("입력 오류! 표기형식을 확인해주세요.")
        }
    }
}