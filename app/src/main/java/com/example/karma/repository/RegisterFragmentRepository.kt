package com.example.karma.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterFragmentRepository(val application: Application) {
    private  var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val isSuccessful = MutableLiveData<Boolean>()

    //request register to firebase
    fun requestRegister(user:String,mail:String,password:String){
        //call firebase service
        firebaseAuth.createUserWithEmailAndPassword(mail, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("register","register success")
                    isSuccessful.value=it.isSuccessful
                }else{
                    Log.d("register","register failed")
                    isSuccessful.value=false
                }

            }
    }
}