package com.example.karma.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.karma.model.Favor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class AskFragmentRepository  {
    private  val database = Firebase.database.reference
    val isSuccessful = MutableLiveData<Boolean>()

    //request creation to firebase
    fun requestCreate(title:String,description:String, user:String){

            //call firebase service
            val favor = Favor(title,description,2,user)
            database.child("favores").push().setValue(favor).addOnCompleteListener{
                if (it.isSuccessful){
                    Log.d("creation","Creation succes")
                    isSuccessful.value=it.isSuccessful
                }else{
                    Log.d("creation","Creation failed")
                    isSuccessful.value=false
                }
            }


    }
}


/*
class AskFragmentRepository (val application: Application) {
    private  val database = Firebase.database
    val isSuccessful = MutableLiveData<Boolean>()

    //request login to firebase
    fun requestCreate(title:String,description:String, user:String){
        //call firebase service
        val myRef = database.getReference("favores")
        myRef.setValue(Favor(title,description,2, user)).addOnCompleteListener{
            if (it.isSuccessful){
                Log.d("creation","Creation succes")
                isSuccessful.value=it.isSuccessful
            }else{
                Log.d("creation","Creation failed")
                isSuccessful.value=false
            }
        }
    }
}
 */