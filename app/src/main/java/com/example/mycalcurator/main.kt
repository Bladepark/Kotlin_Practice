package com.example.mycalcurator
//Lv2 추가 연산 가능하게 하고 출력하기
fun main() {
    println("-------------------계산기-------------------")
    println("--------연산할 첫 번째 숫자를 입력하세요--------")
    var num1 = readLine()!!.toDouble()
    println("--------연산할 두 번째 숫자를 입력하세요--------")
    var num2 = readLine()!!.toDouble()
    println("-------연산자에 해당하는 숫자를 입력하세요-------")
    println("-----1(더하기) 2(빼기) 3(곱하기) 4(나누기)-----")
    var op = readLine()!!.toInt()
    var cal = Calculator()
    var result = cal.calculator(num1, num2, op)
    println("--------------연산 결과 : ${result} --------------")
    do {
        println("-이어서 연산 하시려면 1 종료하시려면 2 입력해주세요-")
        var keepOrExit = readLine()!!.toInt()
        if (keepOrExit == 2) {println("--------계산기 종료--------"); break}
        println("--------연산할 숫자를 입력하세요--------")
        num2 = readLine()!!.toDouble()
        println("-------연산자에 해당하는 숫자를 입력하세요-------")
        println("-----1(더하기) 2(빼기) 3(곱하기) 4(나누기)-----")
        op = readLine()!!.toInt()
        result = cal.calculator(result, num2, op)
        println("--------------연산 결과 : ${result} --------------")
    }while(true)
}