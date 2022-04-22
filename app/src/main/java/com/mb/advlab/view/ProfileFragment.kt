package com.mb.advlab.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mb.advlab.databinding.FragmentProfileBinding
import com.mb.advlab.model.responses.Followeds
import com.mb.advlab.model.responses.GetCount

import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private val viewModel : ProfileViewModel by viewModels()
    private val sharedPrefManager = SharedPrefManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getUserRelations(token,id)
        val token = sharedPrefManager.getSharedPreference(requireContext(),"access_token",null)
        val id = sharedPrefManager.getSharedPreference(requireContext(),"user_id",null)
        getCounts(token,id)
    }

    private fun getCounts(token: String?, id: String?) {
        viewModel.getCounts(token!!,id!!.toLong()).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES -> onSuccesGetCount(it.data!!)
            }
        })
    }

    private fun onSuccesGetCount(responseBody: GetCount) {
        binding.follower.text = "${responseBody.responseBody.follower.toString()}\nFollower"
        binding.following.text =  "${responseBody.responseBody.following.toString()}\nFollowing"
    }

    private fun getUserRelations(token: String?, id: String?) {

        viewModel.getFolloweds(token!!,id!!.toLong()).observe(viewLifecycleOwner,
            {
                when(it.status){
                    Resource.Status.SUCCES -> onSucces(it.data!!)
                }
            })
    }

    private fun onSucces(data: Followeds) {

        Log.i("data",data.responseBody.size.toString())
    }
}