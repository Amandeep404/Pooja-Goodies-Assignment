package com.example.assignment1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment1.R
import com.example.assignment1.databinding.FragmentHomeBinding
import com.example.assignment1.ui.ChannelListViewModel
import com.example.assignment1.ui.MainActivity
import com.example.assignment1.ui.adapter.HomeItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeItemAdapter: HomeItemAdapter
    private val viewModel : ChannelListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Home"

        setUpRecyclerView()
        observeDataFromApi()
    }


    private fun setUpRecyclerView() {
        homeItemAdapter = HomeItemAdapter()
        binding.rvHomePage.apply {
            adapter = homeItemAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }


    private fun observeDataFromApi() {
        viewModel.channelList.observe(viewLifecycleOwner){
            homeItemAdapter.differ.submitList(it.data!!.items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
