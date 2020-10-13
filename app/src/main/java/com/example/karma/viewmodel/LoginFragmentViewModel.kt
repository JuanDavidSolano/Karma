package com.example.karma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.karma.repository.LoginFragmentRepository

class LoginFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository = LoginFragmentRepository(application)
    val isSuccessful: LiveData<Boolean>
    val user: LiveData<String>
    init{
        isSuccessful = repository.isSuccessful
        user = repository.user
    }
    //request login to firebase
    fun requestLogin(mail:String,password:String){
        repository.requestLogin(mail, password)
    }

}