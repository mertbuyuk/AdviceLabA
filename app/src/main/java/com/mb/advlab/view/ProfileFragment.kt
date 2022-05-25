package com.mb.advlab.view

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mb.advlab.adapters.PostAdapter
import com.mb.advlab.databinding.FragmentProfileBinding

import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.BitmapFactory

import android.os.Build
import android.provider.Settings
import android.text.Layout
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.mb.advlab.R
import com.mb.advlab.adapters.IOnClick
import com.mb.advlab.model.responses.*
import com.mb.advlab.utils.ImageHelper
import com.mb.advlab.utils.UriHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import java.io.File
import okhttp3.RequestBody


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private val viewModel : ProfileViewModel by viewModels()
    private val sharedPrefManager = SharedPrefManager()

    private val adapter = PostAdapter()
    private val uriHelper = UriHelper()

    private lateinit var token : String
    private lateinit var id : String

    private val imageHelper = ImageHelper()

    @RequiresApi(Build.VERSION_CODES.O)
    private val pickImageFromGallery = registerForActivityResult( ActivityResultContracts.GetContent()){
        it?.let {
            uploadPhoto(it)
        }}

    @RequiresApi(Build.VERSION_CODES.O)
    private val activityResultLauncher = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()) {result ->
        var allAreGranted = true
        for(b in result.values) {
            allAreGranted = allAreGranted && b
        }

        if(allAreGranted) {
            pickImageFromGallery.launch("image/*")
        }
    }

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
        token = sharedPrefManager.getSharedPreference(requireContext(), "access_token", null)!!
        id = sharedPrefManager.getSharedPreference(requireContext(), "user_id", null)!!

        binding.recyclerView.adapter = adapter

        binding.profileImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val appPerms = arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                )

                activityResultLauncher.launch(appPerms)
            }
        }
        checkPhoto()
        getCounts(token, id)
        getUserPosts(token, id)
        getFollowedDetails(token, id)
        getFollowerDetails(token, id)



        adapter.addListener(object : IOnClick{
            override fun onClick(item: FolloweDDetails) {
            }

            override fun onClick(item: PostResponse) {

                val action = ProfileFragmentDirections.toPostDetails(item.id)
                findNavController().navigate(action)
                }

            })

    }

    private fun checkPhoto() {
        viewModel.getUserPhoto(token,id.toLong(),).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES -> needLoad(it.data!!.responseBody)
            }
        }
        )
    }

    private fun needLoad(responseBody: String) {

        if (responseBody.isNotEmpty()){
            val img = imageHelper.stringToBitmap(responseBody)
            Glide.with(binding.root.context)
                .asBitmap()
                .load(img)
                .centerInside()
                .into(binding.profileImage)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadPhoto(uri: Uri) {
        val imageStream = requireContext().contentResolver.openInputStream(uri);
        val getBitmap = BitmapFactory.decodeStream(imageStream)

        val imageString = uriHelper.convertToString(getBitmap)

        val file = File(uriHelper.getPath(requireContext(),uri));
        val photo = RequestBody.create("application/image".toMediaTypeOrNull(),file)

       val body = MultipartBody.Part.createFormData("image", file.name,photo)

       viewModel.saveUserPhoto(token,id.toLong(),body).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES-> checkPhoto()
//                else -> Log.i("else",it.data!!.responseBody)
            }
        })

    }

    private fun getFollowerDetails(token: String?, id: String?) {
        binding.follower.setOnClickListener {

            val action = ProfileFragmentDirections.actionProfileFragmentToFollowerFragment(id!!.toLong())
            findNavController().navigate(action)
        }
    }

    private fun getFollowedDetails(token: String?, id: String?) {
        binding.following.setOnClickListener {

            val action = ProfileFragmentDirections.actionProfileFragmentToFollowedDetailsFragment(id!!.toLong())
            findNavController().navigate(action)
        }
    }

    private fun getUserPosts(token: String?, id: String?) {
        viewModel.getUsersPost(token!!, id!!.toLong()).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES -> onSuccesGetPost(it.data)
            }
        })
    }

    private fun onSuccesGetPost(data: ResponsePost?) {

        adapter.submitList(data!!.responseBody)
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

    private fun onSucces(data: Followeds) {

        Log.i("data",data.responseBody.size.toString())
    }


    //permission
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton("App Settings",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    // send to app settings if permission is denied permanently
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", requireActivity().packageName, null)
                    intent.data = uri
                    startActivity(intent)
                })
            .setNegativeButton("Cancel",null)
            .show()
    }



}