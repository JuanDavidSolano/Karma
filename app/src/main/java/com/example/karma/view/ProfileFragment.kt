package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.adapters.MainAdapter
import com.example.karma.model.Favor
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var adapter:MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainAdapter(view.context)

        listView.layoutManager = LinearLayoutManager(view.context)
        listView.adapter= adapter

        val dummyList = mutableListOf<Favor>()
        dummyList.add(Favor("Traer a mi novia","Traer a mi novia desde la casqa de su papa",3,"test","test"))
        dummyList.add(Favor("Llorar conmigo","Llorar conmigo porque perdi el parcial",1,"test2","test2"))
        dummyList.add(Favor("Comprarme un chocolate","Por favor que sea un m&m amarillo",2,"test2","test2"))
        dummyList.add(Favor("Traer a mi novia","Traer a mi novia desde la casqa de su papa",3,"test","test"))
        dummyList.add(Favor("Llorar conmigo","Llorar conmigo porque perdi el parcial",1,"test2","test2"))
        dummyList.add(Favor("Comprarme un chocolate","Por favor que sea un m&m amarillo",2,"test2","test2"))

        adapter.setListData(dummyList)
        adapter.notifyDataSetChanged()
    }




}