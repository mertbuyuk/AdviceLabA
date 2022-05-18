package com.mb.advlab.view.relationdetails

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mb.advlab.R
import com.mb.advlab.adapters.RelationAdapters
import com.mb.advlab.databinding.FragmentFollowerBinding
import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : Fragment() {
    private lateinit var binding : FragmentFollowerBinding
    private val viewModel : ProfileViewModel by viewModels()
    private  val args : FollowerFragmentArgs by navArgs()
    private val adapter = RelationAdapters()
    private val sharedPrefManager = SharedPrefManager();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.flag = 1
        followedActBar()
        binding.followerRec.adapter = adapter
        val token = sharedPrefManager.getSharedPreference(requireContext(),"access_token",null)
        getFollowerDetails(token,args.id)
    }

    private fun followedActBar() {
        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar

        actionBar?.title = resources.getString(R.string.follower)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.bgapp)))
    }

    private fun getFollowerDetails(token: String?, id: Long) {

        viewModel.getFollowers(token!!,id).observe(viewLifecycleOwner,{

            when(it.status){
                Resource.Status.SUCCES -> onSucces(it.data!!.responseBody)
            }
        })
    }

    private fun onSucces(responseBody: List<FolloweDDetails>) {

        adapter.submitList(responseBody)
    }
}