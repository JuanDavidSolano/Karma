package com.example.karma.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.karma.model.Favor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileFragmentRepository {
    private  val database = Firebase.database.reference
    private  var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getFavors(): LiveData<MutableList<Favor>> {
        val mutableData = MutableLiveData<MutableList<Favor>>()
        val favorList = mutableListOf<Favor>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favorList.clear()
                for (childDataSnapshot in dataSnapshot.children) {
                    val favor: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    //Log.v("MyOut", "" + childDataSnapshot.getKey()); //displays the key for the node
                    Log.v("MyOut", "" + favor.title);
                    if(favor.status=="Sin asignar" && favor.user!=firebaseAuth.uid.toString()) {
                        favorList.add(favor)
                    }
                }
                mutableData.value = favorList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MyOut", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.child("favores").addValueEventListener(postListener)
        return mutableData
    }

    fun modifyState(title:String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childDataSnapshot in dataSnapshot.children) {
                    val favor: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    Log.v("MyOutA", "" + childDataSnapshot.getKey()); //displays the key for the node
                    Log.v("MyOutA", "" + favor.user);
                    if(favor.title == title && favor.status == "Sin asignar") {
                        database.child("favores").child(childDataSnapshot.getKey().toString()).setValue(Favor(favor.title,favor.description,favor.karma,favor.user,firebaseAuth.uid.toString(),"Asignado"))
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MyOut", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.child("favores").addValueEventListener(postListener)
    }

}