package com.mb.advlab.view

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
import androidx.lifecycle.Observer
import com.mb.advlab.R
import com.mb.advlab.databinding.FragmentSharePostBinding
import com.mb.advlab.model.request.SharePost
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.post.SharePostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SharePostFragment : Fragment() {
    private lateinit var binding : FragmentSharePostBinding
    private val viewModel : SharePostViewModel by viewModels()
    private val sharedPrefManager = SharedPrefManager()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSharePostBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.bgapp)))

        val token = sharedPrefManager.getSharedPreference(requireContext(),"access_token",null)
        val id = sharedPrefManager.getSharedPreference(requireContext(),"user_id",null)

        binding.shareButton.setOnClickListener {
            sharePost(token!!,id!!.toLong())
        }
    }

    fun sharePost(token : String, id : Long){

        val filmName = binding.filmNameTxt.editText?.text.toString()
        val genre = binding.filmGenreTxt.editText?.text.toString()
    //    val rate =  binding.filmPuanTxt.editText?.text.toString()
        val desc = binding.filmDescTxt.editText?.text.toString()

        val sharePost = SharePost(filmName, genre,  desc)

        viewModel.sharePost(token, id,sharePost).observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.SUCCES-> Log.i("mmmm","ssssssssssssss")
            }
        })
    }
}