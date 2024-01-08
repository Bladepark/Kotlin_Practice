package com.example.pumpkinmarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pumpkinmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setAdapter()
        setNotification()
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setNotification() {
        binding.ivMainAlarm.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val builder: NotificationCompat.Builder
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 26 버전 이상
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    // 채널에 다양한 정보 설정
                    description = "My Channel One Description"
                    setShowBadge(true)
                    val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                    setSound(uri, audioAttributes)
                    enableVibration(true)
                }
                // 채널을 NotificationManager에 등록
                manager.createNotificationChannel(channel)

                // 채널을 이용하여 builder 생성
                builder = NotificationCompat.Builder(this, channelId)

            } else {
                // 26 버전 이하
                builder = NotificationCompat.Builder(this)
            }
            // 알림의 기본 정보
            builder.run {
                setSmallIcon(R.mipmap.ic_launcher)
                setWhen(System.currentTimeMillis())
                setContentTitle("키워드 알림")
                setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
            }
            manager.notify(11, builder.build())
        }
    }

    private fun setAdapter() {
        val dataList = mutableListOf<PumpkinItem>()
        dataList.add(
            PumpkinItem(
                R.drawable.sample1,
                getString(R.string.sample1_title),
                getString(R.string.sample1_description),
                getString(R.string.sample1_seller),
                getString(R.string.sample1_address),
                getString(R.string.sample1_price),
                getString(R.string.sample1_chat),
                getString(R.string.sample1_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample2,
                getString(R.string.sample2_title),
                getString(R.string.sample2_description),
                getString(R.string.sample2_seller),
                getString(R.string.sample2_address),
                getString(R.string.sample2_price),
                getString(R.string.sample2_chat),
                getString(R.string.sample2_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample3,
                getString(R.string.sample3_title),
                getString(R.string.sample3_description),
                getString(R.string.sample3_seller),
                getString(R.string.sample3_address),
                getString(R.string.sample3_price),
                getString(R.string.sample3_chat),
                getString(R.string.sample3_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample4,
                getString(R.string.sample4_title),
                getString(R.string.sample4_description),
                getString(R.string.sample4_seller),
                getString(R.string.sample4_address),
                getString(R.string.sample4_price),
                getString(R.string.sample4_chat),
                getString(R.string.sample4_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample5,
                getString(R.string.sample5_title),
                getString(R.string.sample5_description),
                getString(R.string.sample5_seller),
                getString(R.string.sample5_address),
                getString(R.string.sample5_price),
                getString(R.string.sample5_chat),
                getString(R.string.sample5_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample6,
                getString(R.string.sample6_title),
                getString(R.string.sample6_description),
                getString(R.string.sample6_seller),
                getString(R.string.sample6_address),
                getString(R.string.sample6_price),
                getString(R.string.sample6_chat),
                getString(R.string.sample6_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample7,
                getString(R.string.sample7_title),
                getString(R.string.sample7_description),
                getString(R.string.sample7_seller),
                getString(R.string.sample7_address),
                getString(R.string.sample7_price),
                getString(R.string.sample7_chat),
                getString(R.string.sample7_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample8,
                getString(R.string.sample8_title),
                getString(R.string.sample8_description),
                getString(R.string.sample8_seller),
                getString(R.string.sample8_address),
                getString(R.string.sample8_price),
                getString(R.string.sample8_chat),
                getString(R.string.sample8_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample9,
                getString(R.string.sample9_title),
                getString(R.string.sample9_description),
                getString(R.string.sample9_seller),
                getString(R.string.sample9_address),
                getString(R.string.sample9_price),
                getString(R.string.sample9_chat),
                getString(R.string.sample9_like)
            )
        )
        dataList.add(
            PumpkinItem(
                R.drawable.sample10,
                getString(R.string.sample10_title),
                getString(R.string.sample10_description),
                getString(R.string.sample10_seller),
                getString(R.string.sample10_address),
                getString(R.string.sample10_price),
                getString(R.string.sample10_chat),
                getString(R.string.sample10_like)
            )
        )



        val adapter = PumpkinAdapter(dataList)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.addItemDecoration(PumpkinAdapterDecoration())
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            AlertDialog.Builder(this@MainActivity)
                .setIcon(R.drawable.chat)
                .setTitle("종료")
                .setMessage("정말 종료하시겠습니까?")
                .setPositiveButton("확인") { dialog, _ ->
                    finish()
                }
                .setNegativeButton("취소"){ dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}