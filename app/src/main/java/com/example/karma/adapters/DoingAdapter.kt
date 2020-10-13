package com.example.karma.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.model.Favor
import kotlinx.android.synthetic.main.doing_layout.view.*
import kotlinx.android.synthetic.main.favs_layout.view.*

class DoingAdapter (private val context: Context): RecyclerView.Adapter<DoingAdapter.DoingViewHolder>(){
    private var dataList = mutableListOf<Favor>()
    fun setListData(data:MutableList<Favor>){
        dataList = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoingAdapter.DoingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doing_layout, parent, false)
        return DoingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(dataList.size>0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: DoingAdapter.DoingViewHolder, position: Int) {
        val favor = dataList[position]
        holder.bindView(favor)
    }

    inner class DoingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bindView(favor: Favor){
            itemView.textTitle.text = favor.title
            itemView.textKarma.text = "Karma: "+favor.karma
        }
    }
}