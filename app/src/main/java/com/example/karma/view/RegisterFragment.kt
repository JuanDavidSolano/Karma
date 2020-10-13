package com.example.karma.view

import android.os.Bundle
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
import com.example.karma.viewmodel.RegisterFragmentViewModel
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {
    lateinit var navController: NavController

    private lateinit var viewModel: RegisterFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<TextView>(R.id.loginText).setOnClickListener {
            navController!!.navigate(R.id.action_register_login)
        }

        //init viewmodel here
        viewModel = ViewModelProvider(this).get(RegisterFragmentViewModel::class.java)

        registerButton.setOnClickListener{
            println("Register here")
            val mail = view.findViewById<EditText>(R.id.emailInput).text.toString()
            val password = view.findViewById<EditText>(R.id.passwordInput).text.toString()
            val name = view.findViewById<EditText>(R.id.nameInput).text.toString()
            if (isValidData(name,mail,password)) {
                //Request register firebase here
                viewModel.requestRegister(name,mail,password)
            }else{
                Toast.makeText(context,"Porfavor introduzca correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.isSuccessful.observe(viewLifecycleOwner, Observer {
            //handle
            if(!it){
                Toast.makeText(context,"Error al registrarse, intente de nuevo",Toast.LENGTH_SHORT).show()
                view.findViewById<EditText>(R.id.emailInput).setText("")
                view.findViewById<EditText>(R.id.passwordInput).setText("")
                view.findViewById<EditText>(R.id.nameInput).setText("")
            }else{
                Toast.makeText(context,"Registro exitoso",Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_register_login)
            }

        })
    }

    //check valid data
    // true valid - false invalid
    private fun isValidData(name:String,mail:String, password:String): Boolean{
        if(name.isNotEmpty() && mail.isNotEmpty() && password.isNotEmpty()){
            return true
        }
        return false
    }

    }
