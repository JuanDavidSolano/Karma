package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.karma.R
import com.example.karma.viewmodel.AskFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_ask.*


class AskFragment : Fragment() {
    lateinit var navController: NavController

    private lateinit var viewModel: AskFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        backButton.setOnClickListener {
            navController!!.navigate(R.id.action_ask_main)

        }

        backButton2.setOnClickListener {
            navController!!.navigate(R.id.action_ask_main)

        }

        //init viewmodel here
        viewModel = ViewModelProvider(this).get(AskFragmentViewModel::class.java)


        CreateAskButton.setOnClickListener{
            println("Create ask here")
            val title = view.findViewById<EditText>(R.id.titleInput).text.toString()
            val description = view.findViewById<EditText>(R.id.DescriptionInput).text.toString()
            var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            val user = firebaseAuth.uid.toString()
            if (isValidData(title,description, user)) {

                //Request creation firebase here
                viewModel.requestCreate(title,description,user)
            }else{
                Toast.makeText(context,"Porfavor introduzca todos los datos", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isSuccessful.observe(viewLifecycleOwner, Observer {
            //handle
            if(!it){
                Toast.makeText(context,"No puedes solicitar otro favor",Toast.LENGTH_SHORT).show()
                navController!!.navigate(R.id.action_ask_main)
            }else{
                Toast.makeText(context,"Favor creado con exito",Toast.LENGTH_SHORT).show()
                navController!!.navigate(R.id.action_ask_main)
            }

        })

    }

    //check valid data
    // true valid - false invalid
    private fun isValidData(title:String, description:String, user:String): Boolean{
        if(title.isNotEmpty() && description.isNotEmpty() && user.isNotEmpty()){
            return true
        }
        return false
    }

}