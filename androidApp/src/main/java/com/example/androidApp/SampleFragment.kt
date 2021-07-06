package com.example.androidApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidApp.databinding.FragmentSampleBinding
import com.example.shared.Greeting

class SampleFragment : Fragment() {
    private var _binding: FragmentSampleBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = greet()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun greet(): String = Greeting().greeting()
}
