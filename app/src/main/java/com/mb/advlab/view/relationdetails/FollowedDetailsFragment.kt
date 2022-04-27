package com.mb.advlab.view.relationdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mb.advlab.R
import com.mb.advlab.databinding.FragmentFollowedDetailsBinding
import com.mb.advlab.viewmodel.profile.ProfileViewModel

class FollowedDetailsFragment : Fragment() {
    private lateinit var binding : FragmentFollowedDetailsBinding

    private val viewModel : ProfileViewModel by viewModels()
    private val args : FollowedDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowedDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}