package com.mb.advlab.view.relationdetails

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mb.advlab.R
import com.mb.advlab.adapters.IOnClick
import com.mb.advlab.adapters.RelationAdapters
import com.mb.advlab.databinding.FragmentFollowedDetailsBinding
import com.mb.advlab.model.request.FollowUnRequest
import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowedDetailsFragment : Fragment() {
    private lateinit var binding : FragmentFollowedDetailsBinding

    private val viewModel : ProfileViewModel by viewModels()
    private val args : FollowedDetailsFragmentArgs by navArgs()
    private val adapter = RelationAdapters();
    private val sharedPrefManager = SharedPrefManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowedDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followedActBar()
        binding.followedRec.adapter = adapter
        val token = sharedPrefManager.getSharedPreference(requireContext(),"access_token",null)
        getFollowedDetails(token,args.id)
        adapterClickListener(token,args.id)
    }

    private fun adapterClickListener(token: String?, id: Long) {
        adapter.addListener(object :IOnClick{
            override fun onClick(item: FolloweDDetails) {

                when(item.status){
                    1-> unfollow(token,id,item)
                    0-> follow(token,id,item)
                }
            }

        })
    }

    private fun follow(token: String?, fromId: Long, toId: FolloweDDetails) {

    }

    private fun unfollow(token: String?, from: Long, to: FolloweDDetails) {
        val fromTo = FollowUnRequest(from,to.id)
        viewModel.unfollowById(token!!,fromTo).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES -> unfSucces(to)
            }
        })
    }

    private fun unfSucces(to: FolloweDDetails) {

        Log.i("www","succes")
    }

    private fun followedActBar() {
        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar

        actionBar?.title = resources.getString(R.string.following)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.bgapp)))
    }

    private fun getFollowedDetails(token: String?, id: Long) {

        viewModel.getFolloweds(token!!,id).observe(viewLifecycleOwner,{

            when(it.status){
                Resource.Status.SUCCES -> onSucces(it.data?.responseBody)
            }
        })
    }

    private fun onSucces(responseBody: List<FolloweDDetails>?) {

        adapter.submitList(responseBody)
    }
}