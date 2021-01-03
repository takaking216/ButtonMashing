package com.example.buttonmashing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.buttonmashing.databinding.FragmentGameBinding
import java.lang.RuntimeException

class GameFragment : Fragment() {
    private val gameViewModel = GameViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: FragmentGameBinding =
                DataBindingUtil.bind(view)
                        ?: throw
        RuntimeException("FragmentGameBindingでバインディングができない")
        binding.viewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onStart() {
        super.onStart()
        gameViewModel.start()
    }
}