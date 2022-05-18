package com.mb.advlab.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.mb.advlab.databinding.FragmentSignupBinding
import com.mb.advlab.model.responses.BaseModel
import com.mb.advlab.model.request.SignupRequest
import com.mb.advlab.utils.Resource

import com.mb.advlab.viewmodel.signup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private lateinit var binding : FragmentSignupBinding
    private val viewModel : SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signButton.setOnClickListener {
            sendSignReq()
        }

        navigateLogin()
    }

    private fun navigateLogin() {
        binding.loginTextButton.setOnClickListener {
            val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    private fun sendSignReq() {
        val name = binding.signNameTxt.editText?.text.toString()
        val email = binding.signEmailTxt.editText?.text.toString()
        val pasword = binding.signPasswordTxt.editText?.text.toString()
        val request = SignupRequest(name,email,pasword)
        viewModel.signRequest(request).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCES -> onSucces(it.data)
                Resource.Status.ERROR -> onFail(it.message)
            }})
    }

    private fun onFail(data: String?) {
        val gson = Gson()
        val a = gson.fromJson(data, BaseModel::class.java)

        Log.i("message", data!!)
    }

    private fun onSucces(data: BaseModel?) {

        Log.i("message", data?.responseBody!!)
    }
}