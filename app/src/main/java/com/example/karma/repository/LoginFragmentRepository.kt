package com.example.karma.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class LoginFragmentRepository(val application: Application) {

    private  var firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    val isSuccessful = MutableLiveData<Boolean>()
    val user = MutableLiveData<String>()

    //request login to firebase
    fun requestLogin(mail:String,password:String){
        //call firebase service
        firebaseAuth.signInWithEmailAndPassword(mail, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("login","Login succes")
                    isSuccessful.value=it.isSuccessful
                    user.value=firebaseAuth.currentUser.toString()
                    Log.d("login user",user.value.toString())
                }else{
                    Log.d("login","Login failed")
                    isSuccessful.value=false
                }

            }
    }
}