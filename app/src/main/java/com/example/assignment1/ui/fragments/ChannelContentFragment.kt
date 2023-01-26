package com.example.assignment1.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment1.R
import com.example.assignment1.data.models.Item
import com.example.assignment1.data.searchModel.SearchItem
import com.example.assignment1.databinding.FragmentChannelContentBinding
import com.example.assignment1.ui.ChannelListViewModel
import com.example.assignment1.ui.MainActivity
import com.example.assignment1.ui.adapter.ChannelVideoItemAdapter
import com.example.assignment1.ui.fragments.HomeFragment.Companion.CHANNEL_ID
import com.example.assignment1.ui.fragments.HomeFragment.Companion.CHANNEL_NAME
import com.example.assignment1.utils.Resource
import kotlinx.android.synthetic.main.fragment_channel_content.*
import kotlin.math.log


class ChannelContentFragment : Fragment(R.layout.fragment_channel_content), (SearchItem) -> Unit {

    private var _binding : FragmentChannelContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var channelItemAdapter : ChannelVideoItemAdapter
    private lateinit var viewModel : ChannelListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChannelContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

       val channelName = arguments?.getString(CHANNEL_NAME)
        val channelId = arguments?.getString(CHANNEL_ID)
        binding.toolbarDesc.title = channelName
        binding.toolbarDesc.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_channelFragment_to_homeFragment)
        }

        channelItemAdapter = ChannelVideoItemAdapter(this)
        binding.rvChannelContent.apply {
            adapter = channelItemAdapter
            layoutManager = LinearLayoutManager(activity)

        }

       viewModel.fetchPopularVideos(channelId!!)
        viewModel.popularVideoList.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Success ->{
                    hideProgressBar()
                    it.data?.let {response ->
                        channelItemAdapter.channelDiffer.submitList(response.items)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    it.message?.let {
                        Log.e(HomeFragment.TAG, "An error occurred: $it")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun setUpRecyclerView() {

    }


    private fun observeDataFromApi() {
        viewModel.popularVideoList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    hideProgressBar()
                    it.data?.let {response ->
                        channelItemAdapter.channelDiffer.submitList(response.items)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    it.message?.let {
                        Log.e(HomeFragment.TAG, "An error occurred: $it")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationChannelContentProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.paginationChannelContentProgressBar.visibility = View.VISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(p1: SearchItem) {

    }

}