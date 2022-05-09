package com.mb.advlab.view.explore

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mb.advlab.R
import com.mb.advlab.adapters.PostAdapter
import com.mb.advlab.databinding.FragmentExploreBinding
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.utils.UriHelper
import com.mb.advlab.viewmodel.explore.ExplorerViewModel
import com.mb.advlab.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private lateinit var binding : FragmentExploreBinding
    private lateinit var token : String
    private lateinit var id : String

    private val viewModel : ExplorerViewModel by viewModels()
    private val sharedPrefManager = SharedPrefManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExploreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = sharedPrefManager.getSharedPreference(requireContext(), "access_token", null)!!
     //   id = sharedPrefManager.getSharedPreference(requireContext(), "user_id", null)!!
        searchListener()
    }


    private fun searchListener() {
        binding.searchUser.setOnClickListener {
            val action = ExploreFragmentDirections.toSearchFragment()
            findNavController().navigate(action)
            Log.i("tag","done")
        }
    }
    }
