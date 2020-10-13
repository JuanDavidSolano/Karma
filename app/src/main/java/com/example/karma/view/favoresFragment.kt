package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karma.R
import com.example.karma.adapters.DoingAdapter
import com.example.karma.adapters.PersonalFavorsAdapter
import com.example.karma.model.Favor
import kotlinx.android.synthetic.main.fragment_favores.*
import kotlinx.android.synthetic.main.fragment_mainhome.*

class favoresFragment : Fragment() {

    private lateinit var adapter: DoingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DoingAdapter(view.context)

        doingView.layoutManager = LinearLayoutManager(view.context)
        doingView.adapter= adapter

        val dummyList = mutableListOf<Favor>()
        dummyList.add(Favor("Traer a mi novia","Traer a mi novia desde la casqa de su papa",3,"test","test","Sin asignar"))
        dummyList.add(Favor("Llorar conmigo","Llorar conmigo porque perdi el parcial",1,"test2","test2","Asignado"))
        dummyList.add(Favor("Comprarme un chocolate","Por favor que sea un m&m amarillo",2,"test2","test2","Entregado"))
        dummyList.add(Favor("Traer a mi novia","Traer a mi novia desde la casqa de su papa",3,"test","test","Sin asignar"))
        dummyList.add(Favor("Llorar conmigo","Llorar conmigo porque perdi el parcial",1,"test2","test2","Sin asignar"))
        dummyList.add(Favor("Comprarme un chocolate","Por favor que sea un m&m amarillo",2,"test2","test2","Entregado"))

        adapter.setListData(dummyList)
        adapter.notifyDataSetChanged()
    }

}