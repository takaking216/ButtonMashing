package com.example.buttonmashing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buttonmashing.databinding.FragmentResultBinding
import java.lang.RuntimeException

class ResultFragment : Fragment() {
    private val mainViewModel: MainSharedViewModel by activityViewModels()

    val viewModel = ResultViewModel()
    val adapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.history.observe(viewLifecycleOwner, Observer {
            adapter.update(it ?: listOf())
        })

        val binding: FragmentResultBinding =
            DataBindingUtil.bind(view) ?: throw
                    RuntimeException("FragmentResultBindingでバインドできない")

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.historyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false
            )
        binding.historyRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        val count = arguments?.getInt("count", 0) ?: 0
        viewModel.count.value = count

        mainViewModel.history.value?.toString()?.let {
            Log.d("count:", it)
        }
    }
}