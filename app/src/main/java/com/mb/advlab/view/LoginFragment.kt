package com.mb.advlab.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mb.advlab.databinding.FragmentLoginBinding
import com.mb.advlab.model.LoginRequest
import com.mb.advlab.model.responses.LoginResponse
import com.mb.advlab.utils.Resource
import com.mb.advlab.utils.SharedPrefManager
import com.mb.advlab.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    private val sharedPrefManager = SharedPrefManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            sendLoginRequest()
        }

        clickListeners()
    }

    private fun clickListeners() {
        binding.registerTextButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

    private fun sendLoginRequest() {
        val email = binding.loginEmailTxt.editText?.text.toString()
        val password = binding.loginPassword.editText?.text.toString()
        val request = LoginRequest(email,password)
        viewModel.loginRequest(request).observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.SUCCES -> onSucces(it.data)
                Resource.Status.LOADING -> Log.i("aaaa", "beklemede")
            }
        })
    }

    private fun onSucces(data: LoginResponse?) {
        sharedPrefManager.setSharedPreference(requireContext(),"access_token",data?.responseBody?.jwt)
        sharedPrefManager.setSharedPreference(requireContext(),"user_id",data?.responseBody?.id.toString())

        val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
        findNavController().navigate(action)
        Log.i("aaaa",data?.responseBody!!.jwt)
    }
}