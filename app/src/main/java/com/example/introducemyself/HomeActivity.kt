package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeImgView = findViewById<ImageView>(R.id.img_home)
        val idTextView = findViewById<TextView>(R.id.tv_homeId)
        val nameTextView = findViewById<TextView>(R.id.tv_homeName)
        val ageTextView = findViewById<TextView>(R.id.tv_homeAge)
        val mbtiTextView = findViewById<TextView>(R.id.tv_homeMbti)
        val homeExitBtn = findViewById<Button>(R.id.btn_homeExit)
        val webView = findViewById<WebView>(R.id.webView)
        val blogTextView = findViewById<TextView>(R.id.tv_homeBlog)
        val githubTextView = findViewById<TextView>(R.id.tv_homeGithub)

        val id = intent.getStringExtra("memberId")
        val pwd = intent.getStringExtra("memberPwd")

        val memberInfo = MemberInfo.memberInfo.find { it.id == id && it.pwd == pwd }

        val imgResources = arrayOf(R.drawable.bbangbbang1,R.drawable.bbangbbang2,R.drawable.bbangbbang3,
            R.drawable.bbangbbang4,R.drawable.bbangbbang5,R.drawable.bbangbbang6,R.drawable.bbangbbang7)

        val random = Random.nextInt(imgResources.size)

        // WebView 설정 및 JavaScript 활성화
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        // WebViewClient를 설정하여 새 창이 아니라 현재 WebView에서 링크가 열리도록 함
        webView.webViewClient = WebViewClient()

        homeImgView.setImageResource(imgResources[random])
        idTextView.setText("아이디 :" + id)
        nameTextView.setText("이름 :" + memberInfo?.name)
        ageTextView.setText("나이 :" + memberInfo?.age)
        mbtiTextView.setText("MBTI :" + memberInfo?.mbti)

        blogTextView.setOnClickListener {
            val url = "https://happenedtodeveloper.tistory.com/" // 원하는 링크 주소로 변경
            webView.visibility = WebView.VISIBLE
            webView.loadUrl(url)
        }

        githubTextView.setOnClickListener {
            val url = "https://github.com/Bladepark" // 원하는 링크 주소로 변경
            webView.visibility = WebView.VISIBLE
            webView.loadUrl(url)
        }

        homeExitBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("memberId", id)
            intent.putExtra("memberPwd", pwd)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}