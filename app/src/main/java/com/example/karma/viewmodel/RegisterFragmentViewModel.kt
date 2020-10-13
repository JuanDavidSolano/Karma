package com.example.karma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.karma.repository.RegisterFragmentRepository

class RegisterFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RegisterFragmentRepository(application)
    val isSuccessful: LiveData<Boolean>
    init{
        isSuccessful = repository.isSuccessful
    }
    //request login to firebase
    fun requestRegister(name:String, mail:String,password:String){
        repository.requestRegister(name, mail, password)
    }
}