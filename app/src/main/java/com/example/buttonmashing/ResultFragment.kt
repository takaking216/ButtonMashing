package com.example.buttonmashing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.buttonmashing.databinding.FragmentResultBinding
import java.lang.RuntimeException

class ResultFragment : Fragment() {
    val viewModel = ResultViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentResultBinding =
            DataBindingUtil.bind(view) ?: throw
                    RuntimeException("FragmentResultBindingでバインドできない")
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        val count = arguments?.getInt("count", 0) ?: 0
        viewModel.count.value = count
    }
}