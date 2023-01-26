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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment1.R
import com.example.assignment1.data.models.Item
import com.example.assignment1.databinding.FragmentHomeBinding
import com.example.assignment1.ui.ChannelListViewModel
import com.example.assignment1.ui.MainActivity
import com.example.assignment1.ui.adapter.HomeItemAdapter
import com.example.assignment1.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home){

    companion object{
       const val TAG = "LIST_OF_CHANNELS"
        const val CHANNEL_ID = "channelId"
        const val CHANNEL_NAME = "channelName"
    }

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
        setUpRecyclerView()
        observeDataFromApi()

        homeItemAdapter.setOnItemClickListener {

            Log.e(CHANNEL_ID, it.id)

            val bundle = Bundle().apply {
                putString(CHANNEL_ID, it.id)
                putString(CHANNEL_NAME, it.snippet.title)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_channelFragment,bundle
            )

        }

    }


    private fun setUpRecyclerView() {
        homeItemAdapter = HomeItemAdapter()
        binding.rvHomePage.apply {
            adapter = homeItemAdapter
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        }
    }


    private fun observeDataFromApi() {
        viewModel.channelList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    hideProgressBar()
                    it.data?.let {response ->
                        homeItemAdapter.differ.submitList(response.items)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    it.message?.let {
                        Log.e(TAG, "An error occurred: $it")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }


}
