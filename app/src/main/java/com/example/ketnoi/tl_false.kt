package com.example.ketnoi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ketnoi.databinding.FragmentTlFalseBinding

class tl_false : Fragment() {
    private var _binding: FragmentTlFalseBinding? = null
    private val binding get() = _binding!!
    private var exitCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTlFalseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnExit.setOnClickListener {
            handleExit()
        }

        binding.btnLamLai.setOnClickListener {

            resetGame()
        }
    }

    private fun handleExit() {
        exitCount++
        if (exitCount == 1) {
            Toast.makeText(context, "Ấn lần nữa để thoát trò chơi", Toast.LENGTH_SHORT).show()
        } else if (exitCount == 2) {
            requireActivity().finish()
        }
    }

    private fun resetGame() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, cau_1())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
