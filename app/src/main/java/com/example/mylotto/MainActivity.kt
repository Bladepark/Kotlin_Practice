package com.example.mylotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.size

class MainActivity : AppCompatActivity() {

    private val resetButton by lazy { findViewById<Button>(R.id.btn_reset) }
    private val randomButton by lazy { findViewById<Button>(R.id.btn_random) }
    private val numaddButton by lazy { findViewById<Button>(R.id.btn_numadd) }
    private val numberPicker by lazy { findViewById<NumberPicker>(R.id.numberPicker) }

    private val numTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.np_num1),
            findViewById(R.id.np_num2),
            findViewById(R.id.np_num3),
            findViewById(R.id.np_num4),
            findViewById(R.id.np_num5),
            findViewById(R.id.np_num6)
        )
    }

    private var didrandom = false
    private val numberPickSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initResetButton()
        initrandomButton()
        initNumAddButton()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initNumAddButton() {
        numaddButton.setOnClickListener {
            when {
                didrandom -> showToast("초기화를 해주세요.")
                numberPickSet.size >= 5 -> showToast("최대 5개까지만 선택할 수 있습니다.")
                numberPickSet.contains(numberPicker.value) -> showToast("이미 선택된 숫자 입니다.")
                else -> {
                    val textView = numTextViewList[numberPickSet.size]
                    textView.isVisible = true
                    textView.text = numberPicker.value.toString()
                    setNumberBackGroundColor(numberPicker.value, textView)
                    numberPickSet.add(numberPicker.value)
                }
            }
        }
    }

    private fun setNumberBackGroundColor(number: Int, textView: TextView) {
        val background = when (number) {
            in 1..10 -> R.drawable.circle_yellow
            in 11..20 -> R.drawable.circle_blue
            in 21..30 -> R.drawable.circle_red
            in 31..40 -> R.drawable.circle_gray
            else -> R.drawable.circle_green
        }
        textView.background = ContextCompat.getDrawable(this, background)
    }


    private fun initResetButton() {
        resetButton.setOnClickListener {
            numberPickSet.clear()
            numTextViewList.forEach { it.isVisible = false }
            didrandom = false
            numberPicker.value = 1
        }
    }

    private fun initrandomButton() {
        randomButton.setOnClickListener {
            val list = getRandom()

            didrandom = true

            list.forEachIndexed { index, number ->
                val textView = numTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true
                setNumberBackGroundColor(number, textView)
            }
        }
    }

    private fun getRandom(): List<Int> {
        val numbers = (1..45).filter { it !in numberPickSet }
        return (numberPickSet + numbers.shuffled().take(6-numberPickSet.size)).sorted()
    }

}