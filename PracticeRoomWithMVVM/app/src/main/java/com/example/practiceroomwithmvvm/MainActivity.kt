package com.example.practiceroomwithmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceroomwithmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val wordListAdapter by lazy { WordListAdapter() }

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initView() {
        setWordListAdapter()
        setResultLauncher()
        onFabPressed()
    }

    private fun setWordListAdapter() {
        binding.recyclerview.apply {
            adapter = wordListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    // 결과가 성공인 경우 처리
                    val data: Intent? = result.data
                    data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                        val word = Word(reply)
                        wordViewModel.insert(word)
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun onFabPressed() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun initViewModel() {
        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { wordListAdapter.submitList(it) }
        }
    }
}