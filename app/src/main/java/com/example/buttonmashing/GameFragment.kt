package com.example.buttonmashing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.buttonmashing.databinding.FragmentGameBinding
import java.lang.RuntimeException
import androidx.fragment.app.activityViewModels

class GameFragment : Fragment() {
    private val gameViewModel = GameViewModel()
    private val mainViewModel: MainSharedViewModel by activityViewModels()

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
        gameViewModel.start {
            mainViewModel.history.value?.add(it)
            findNavController().navigate(
                R.id.action_gameFragment_to_resultFragment,
                Bundle().apply {
                    putInt("count", it)
                }
            )
        }
    }
}