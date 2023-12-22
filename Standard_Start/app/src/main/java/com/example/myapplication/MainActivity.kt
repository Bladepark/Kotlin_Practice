package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var isFabOpen = false
    private val REQUEST_STORAGE = 1000
    private val REQUEST_CALL = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgCbum = findViewById<ImageView>(R.id.iv_cbum)
        imgCbum.setOnClickListener {
            val animationLeft = ObjectAnimator.ofFloat(it, "translationx", 0f)
            animationLeft.duration = 1000
            val animationRight = ObjectAnimator.ofFloat(it, "translationX", 500f)
            animationRight.duration = 1000

            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(animationRight, animationLeft)
            animatorSet.start()
        }

        val resultText = findViewById<TextView>(R.id.tv_result_text)
        val submitBtn = findViewById<Button>(R.id.btn_submit)
        val editText = findViewById<EditText>(R.id.et_text)
        submitBtn.setOnClickListener {
            val inputText = editText.text.toString()
            resultText.text = inputText
        }

        val fabMain = findViewById<FloatingActionButton>(R.id.fab_main)
        val fabPhoto = findViewById<FloatingActionButton>(R.id.fab_pick_image)
        val fabCall = findViewById<FloatingActionButton>(R.id.fab_phone_call)
        fabMain.setOnClickListener {
            if (isFabOpen) {
                ObjectAnimator.ofFloat(fabPhoto, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(fabCall, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(fabMain, View.ROTATION, 45f, 0f).apply { start() }
            } else {
                ObjectAnimator.ofFloat(fabPhoto, "translationY", -360f).apply { start() }
                ObjectAnimator.ofFloat(fabCall, "translationY", -180f).apply { start() }
                ObjectAnimator.ofFloat(fabMain, View.ROTATION, 0f, 45f).apply { start() }
            }
            isFabOpen = !isFabOpen
        }

        fabPhoto.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.i("BUTTON", "Photo Granted")
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    Log.i("BUTTON", "ShowPermission")
                }
                else -> {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_STORAGE
                    )
                    Log.i("BUTTON", "else")
                }
            }
        }

        fabCall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:1012345678")
                startActivity(callIntent)
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    REQUEST_CALL
                )
            }
        }


    }
}