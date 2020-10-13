package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karma.R
import com.example.karma.adapters.PersonalFavorsAdapter
import com.example.karma.model.Favor
import kotlinx.android.synthetic.main.fragment_mainhome.*


class mainhomeFragment : Fragment() {

    private lateinit var adapter: PersonalFavorsAdapter
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mainhome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        adapter = PersonalFavorsAdapter(view.context)

        favorView.layoutManager = LinearLayoutManager(view.context)
        favorView.adapter= adapter

        val dummyList = mutableListOf<Favor>()
        dummyList.add(Favor("Traer a mi novia","Traer a mi novia desde la casqa de su papa",3,"test","test","Sin asignar"))
        dummyList.add(Favor("Llorar conmigo","Llorar conmigo porque perdi el parcial",1,"test2","test2","Asignado"))
        dummyList.add(Favor("Comprarme un chocolate","Por favor que sea un m&m amarillo",2,"test2","test2","Entregado"))
        dummyList.add(Favor("Traer a mi novia","Traer a mi novia desde la casqa de su papa",3,"test","test","Sin asignar"))
        dummyList.add(Favor("Llorar conmigo","Llorar conmigo porque perdi el parcial",1,"test2","test2","Sin asignar"))
        dummyList.add(Favor("Comprarme un chocolate","Por favor que sea un m&m amarillo",2,"test2","test2","Entregado"))

        adapter.setListData(dummyList)
        adapter.notifyDataSetChanged()


        textPedirUnFavor.setOnClickListener {
            navController!!.navigate(R.id.action_home_ask)

        }

    }

}