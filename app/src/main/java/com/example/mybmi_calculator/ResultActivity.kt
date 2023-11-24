package com.example.mybmi_calculator

import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        var BMIResult = weight / ((height/100.0) * (height/100.0))

        var resText = ""
        var resImg = 0
        var resTextColor = 0

        if(BMIResult<18.5) {
            resText = "저체중입니다."
            resImg = R.drawable.lv1
            resTextColor = Color.BLUE
        }
        else if (BMIResult>=18.5 && BMIResult<25.0){
            resText = "정상체중입니다."
            resImg = R.drawable.lv2
            resTextColor = Color.GREEN
        }
        else if (BMIResult>=25.0 && BMIResult<30.0){
            resText = "과체중입니다."
            resImg = R.drawable.lv3
            resTextColor = Color.YELLOW
        }
        else if (BMIResult>=30.0 && BMIResult<35.0){
            resText = "경도비만입니다."
            resImg = R.drawable.lv4
            resTextColor = Color.CYAN
        }
        else if (BMIResult>=35.0 && BMIResult<40.0){
            resText = "중도비만입니다."
            resImg = R.drawable.lv5
            resTextColor = Color.MAGENTA
        }
        else if (BMIResult>=40){
            resText = "고도비만입니다."
            resImg = R.drawable.lv6
            resTextColor = Color.RED
        }

        val tv_resValue = findViewById<TextView>(R.id.tv_resValue)
        val tv_resText = findViewById<TextView>(R.id.tv_resText)
        val iv_resImg = findViewById<ImageView>(R.id.iv_resImg)
        val btn_resBtn = findViewById<Button>(R.id.btn_resBtn)

        tv_resValue.text = BMIResult.toString()
        tv_resText.text = resText
        iv_resImg.setImageResource(resImg)
        tv_resText.setTextColor(resTextColor)

        btn_resBtn.setOnClickListener() {
            finish()
        }
    }
}