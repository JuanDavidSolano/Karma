package com.example.karma.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.karma.R
import com.example.karma.viewmodel.LoginFragmentViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment() {
    lateinit var navController: NavController

    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<TextView>(R.id.registerText).setOnClickListener {
            navController!!.navigate(R.id.action_login_register)
        }

        //init viewmodel here
        viewModel = ViewModelProvider(this).get(LoginFragmentViewModel::class.java)

        loginButton.setOnClickListener{
            println("Login here")
            val mail = view.findViewById<EditText>(R.id.emailInput).text.toString()
            val password = view.findViewById<EditText>(R.id.passwordInput).text.toString()
            if (isValidData(mail,password)) {
                //Request login firebase here
                viewModel.requestLogin(mail,password)
            }else{
                Toast.makeText(context,"Porfavor introduzca correo y contraseña",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isSuccessful.observe(viewLifecycleOwner, Observer {
            //handle
            if(!it){
                Toast.makeText(context,"Correo o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            }else{
                navController!!.navigate(R.id.action_login_main)
            }

        })


    }

    //check valid data
    // true valid - false invalid
    private fun isValidData(mail:String, password:String): Boolean{
        if(mail.isNotEmpty() && password.isNotEmpty()){
            return true
        }
        return false
    }

}