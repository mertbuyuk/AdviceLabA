package com.mb.advlab.view.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.mb.advlab.R
import com.mb.advlab.databinding.FragmentPostDetailsBinding
import com.mb.advlab.model.responses.PostResponse
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.postdetails.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    private lateinit var binding : FragmentPostDetailsBinding
    private val args : PostDetailsFragmentArgs by navArgs()
    private val viewModel : PostDetailsViewModel by viewModels()
    private val sharedPref = SharedPrefManager()

    private lateinit var token : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = sharedPref.getSharedPreference(requireContext(), "access_token", null)!!

        getpostDetails(token)
    }

    private fun getpostDetails(token : String) {

        viewModel.getPostDetails(token,args.id).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES -> onSucces(it.data)
            }
        })
    }

    private fun onSucces(data: PostResponse?) {
        Log.i("bind",data.toString())
        binding.apply {

        }
    }
}