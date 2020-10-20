package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karma.R
import com.example.karma.adapters.PersonalFavorsAdapter
import com.example.karma.adapters.PersonalFavorsAdapter.onFavorClickListener
import com.example.karma.model.Favor
import com.example.karma.viewmodel.ProfileFragmentViewModel
import com.example.karma.viewmodel.mainHomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_mainhome.*


class mainhomeFragment : Fragment(), onFavorClickListener {

    private lateinit var adapter: PersonalFavorsAdapter
    lateinit var navController: NavController
    private val viewModel by lazy{ ViewModelProvider(this).get(mainHomeFragmentViewModel::class.java) }

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

        adapter = PersonalFavorsAdapter(view.context, this)

        favorView.layoutManager = LinearLayoutManager(view.context)
        favorView.adapter= adapter
        observeFavors()


        textPedirUnFavor.setOnClickListener {
            navController!!.navigate(R.id.action_home_ask)

        }

    }

    fun observeFavors(){
        viewModel.fetchUserData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(item: Favor, position: Int) {
        viewModel.modifyState(item.title)
    }


}