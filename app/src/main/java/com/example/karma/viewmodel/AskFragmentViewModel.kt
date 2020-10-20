package com.example.karma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.karma.repository.AskFragmentRepository

class AskFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = AskFragmentRepository()
    val isSuccessful: LiveData<Boolean>
    init{
        isSuccessful = repository.isSuccessful
    }
    //request login to firebase
    fun requestCreate(title:String,description:String, user:String){
        repository.requestCreate(title, description, user)
    }
}