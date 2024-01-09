package com.example.pumpkinmarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.res.ColorStateList
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pumpkinmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setAdapter()
        setNotification()
        setFAB()
        setActivityResultLauncher()
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setActivityResultLauncher() {

    }

    private fun setFAB() {
        var isTop = true

        binding.mainRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (binding.mainRecyclerView.canScrollVertically(-1).not() && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.floatingActionButton.startAnimation(AlphaAnimation(1f, 0f).apply { duration = 500 }) // AlphaAnimation 투명도를 조절하는 클래스
                    binding.floatingActionButton.visibility = View.INVISIBLE
                    isTop = true
                } else {
                    if (isTop) {
                        binding.floatingActionButton.visibility = View.VISIBLE
                        binding.floatingActionButton.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 500 })
                        binding.floatingActionButton.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.white
                            )
                        )
                        isTop = false
                    }
                }
            }
        })

        binding.floatingActionButton.setOnClickListener {
            binding.mainRecyclerView.smoothScrollToPosition(0)
            binding.floatingActionButton.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.pumpkin))
        }

    }

    private fun setNotification() {
        binding.ivMainAlarm.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val builder: NotificationCompat.Builder
            val channelId = "Pumpkin-channel"
            val channelName = "Pumpkin Market Channel"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Pumpkin Market Channel Notification"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)

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
                getString(R.string.sample1_like).toInt(),
                false
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
                getString(R.string.sample2_like).toInt(),
                false
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
                getString(R.string.sample3_like).toInt(),
                false
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
                getString(R.string.sample4_like).toInt(),
                false
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
                getString(R.string.sample5_like).toInt(),
                false
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
                getString(R.string.sample6_like).toInt(),
                false
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
                getString(R.string.sample7_like).toInt(),
                false
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
                getString(R.string.sample8_like).toInt(),
                false
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
                getString(R.string.sample9_like).toInt(),
                false
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
                getString(R.string.sample10_like).toInt(),
                false
            )
        )

        val adapter = PumpkinAdapter(dataList)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.addItemDecoration(PumpkinAdapterDecoration())

        adapter.itemClick = object : PumpkinAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickedItem = dataList[position]
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("likePosition", position)
                intent.putExtra("pumpkinItem", clickedItem)
                activityResultLauncher.launch(intent)
            }
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val likePosition = it.data?.getIntExtra("likePosition",0) as Int
                val isLiked = it.data?.getBooleanExtra("isLiked",false) as Boolean
                if (isLiked) {
                    dataList[likePosition].isLiked = true
                    dataList[likePosition].itemLike += 1
                } else {
                    if (dataList[likePosition].isLiked) {
                        dataList[likePosition].isLiked = false
                        dataList[likePosition].itemLike -= 1
                    }
                }
                adapter.notifyItemChanged(likePosition)
            }
        }

        adapter.itemLongClick = object : PumpkinAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                val longClickedItem = dataList[position]
                AlertDialog.Builder(this@MainActivity)
                    .setIcon(R.drawable.chat)
                    .setTitle("상품 삭제")
                    .setMessage("상품을 정말로 삭제하시겠습니까?")
                    .setPositiveButton("확인") { dialog, _ ->
                        dataList.remove(longClickedItem)
                        adapter.notifyDataSetChanged()
                    }
                    .setNegativeButton("취소") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        }
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
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}