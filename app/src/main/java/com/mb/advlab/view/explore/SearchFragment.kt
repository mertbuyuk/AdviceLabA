package com.mb.advlab.view.explore

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.mb.advlab.R
import com.mb.advlab.adapters.PostAdapter
import com.mb.advlab.adapters.RelationAdapters
import com.mb.advlab.databinding.FragmentExploreBinding
import com.mb.advlab.databinding.FragmentSearchBinding
import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.explore.ExplorerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private lateinit var token : String
    private lateinit var id : String

    private val viewModel : ExplorerViewModel by viewModels()
    private val sharedPrefManager = SharedPrefManager()
    private val adapter = RelationAdapters()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = sharedPrefManager.getSharedPreference(requireContext(), "access_token", null)!!
        binding.searchedRec.adapter = adapter
        adapter.flag = 1

        searchListener()
        appbar()
    }

    private fun appbar() {
        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.bgapp)))
        actionBar?.show()
    }

    private fun searchListener() {

        binding.mainSearchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener,android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchUser(token,newText!!).observe(viewLifecycleOwner,{
                    when(it.status){
                        Resource.Status.SUCCES-> onSucces(it.data!!.responseBody)
                    }
                })
                return true
            }

        } )
    }

    private fun onSucces(responseBody: List<FolloweDDetails>) {
        adapter.submitList(responseBody)
    }
}