package com.example.practicefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.practicefragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentDataListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnFragment1.setOnClickListener {
                // Activity -> FirstFrament
                val dataToSend = "Hello First Fragment\n from Activity"
                val fragment = FirstFragment.newInstance(dataToSend)
                setFragment(fragment)
            }
            btnFragment2.setOnClickListener {
                // Activity -> SecondFrament
                val dataToSend = "Hello Second Fragment\n from Activity"
                val fragment = SecondFragment.newInstance(dataToSend)
                setFragment(fragment)
            }
        }
        setFragment(FirstFragment())                // 처음 시작할 때 띄울 화면
    }


    private fun setFragment(frag : Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)         // 지정해놓은 화면에 교체할 화면 넣기
            setReorderingAllowed(true)              // 이 메소드는 transaction과 관련된 프래그먼트의 상태 변경을 최적화하여 애니메이션과 전환이 올바르게 작동하도록 한다.
            addToBackStack("")                // 뒤로가기 기능 구현을 위한 스택에 프래그먼트 추가
        }
    }

    override fun onDataReceived(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}