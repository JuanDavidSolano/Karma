package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karma.R
import com.example.karma.adapters.DoingAdapter
import com.example.karma.adapters.PersonalFavorsAdapter
import com.example.karma.model.Favor
import com.example.karma.viewmodel.favoresFragmentViewModel
import com.example.karma.viewmodel.mainHomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_favores.*
import kotlinx.android.synthetic.main.fragment_mainhome.*
import com.example.karma.adapters.DoingAdapter.onFavorClickListener

class favoresFragment : Fragment(), onFavorClickListener {

    private lateinit var adapter: DoingAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(favoresFragmentViewModel::class.java) }

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

        adapter = DoingAdapter(view.context, this)

        doingView.layoutManager = LinearLayoutManager(view.context)
        doingView.adapter= adapter
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