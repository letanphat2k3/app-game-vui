package com.example.ketnoi

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ketnoi.databinding.FragmentCau2Binding

class cau_2 : Fragment() {
    private var _binding: FragmentCau2Binding? = null
    private val binding get() = _binding!!

    private lateinit var timer: CountDownTimer
    private val timeLimit: Long = 10000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCau2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val correctAnswerId = binding.btnDapAnAcau2.id

        binding.btnDapAnAcau2.setOnClickListener { checkAnswer(it.id, correctAnswerId) }
        binding.btnDapAnBcau2.setOnClickListener { checkAnswer(it.id, correctAnswerId) }
        binding.btnDapAnCcau2.setOnClickListener { checkAnswer(it.id, correctAnswerId) }
        binding.btnDapAnDcau2.setOnClickListener { checkAnswer(it.id, correctAnswerId) }
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLimit, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.timerTextView.text = "Thời gian còn lại: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                goToLossFragment()
            }
        }.start()
    }

    private fun checkAnswer(selectedAnswerId: Int, correctAnswerId: Int) {
        timer.cancel()
        if (selectedAnswerId == correctAnswerId) {
            goToCau3Fragment()
        } else {
            goToLossFragment()
        }
    }

    private fun goToCau3Fragment() {
        val fragment = cau_3()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun goToLossFragment() {
        val fragment = tl_false()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }
}