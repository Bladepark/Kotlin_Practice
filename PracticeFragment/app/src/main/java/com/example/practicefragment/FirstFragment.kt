package com.example.practicefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicefragment.databinding.FragmentFirstBinding
import com.example.practicefragment.databinding.FragmentSecondBinding

private const val ARG_PARAM1 = "param1"

class FirstFragment : Fragment() {
    private var param1: String? = null
    private lateinit var binding : FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // [1] Activity -> FirstFragment
        binding.tvFragment1.text = param1

        // [2] FirstFragment -> SecondFragment
        binding.btnGofrag2.setOnClickListener {
            val dataToSend = "Hello Second Fragment \n from First Fragment"
            val fragment2 = SecondFragment.newInstance(dataToSend)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment2)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}