package com.example.karma.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.karma.model.Favor
import com.example.karma.repository.ProfileFragmentRepository
import com.example.karma.view.ProfileFragment

class ProfileFragmentViewModel: ViewModel() {

    private val repository = ProfileFragmentRepository()

    fun fetchUserData():LiveData<MutableList<Favor>>{
        val mutableData = MutableLiveData<MutableList<Favor>>()
        repository.getFavors().observeForever{
            mutableData.value=it
        }
        return mutableData
    }

    fun modifyState(title:String){
        repository.modifyState(title)
    }

}