package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karma.R
import com.example.karma.adapters.MainAdapter
import com.example.karma.model.Favor
import com.example.karma.viewmodel.ProfileFragmentViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import com.example.karma.adapters.MainAdapter.onFavorClickListener

class ProfileFragment : Fragment(), onFavorClickListener {

    private lateinit var adapter:MainAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(ProfileFragmentViewModel::class.java) }

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

        adapter = MainAdapter(view.context, this)
        listView.layoutManager = LinearLayoutManager(view.context)
        listView.adapter= adapter
        observeFavors()
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

