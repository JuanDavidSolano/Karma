package com.example.karma.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.model.Favor
import kotlinx.android.synthetic.main.list_layout.view.*

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var dataList = mutableListOf<Favor>()

    fun setListData(data:MutableList<Favor>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(dataList.size>0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val favor = dataList[position]
        holder.bindView(favor)
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bindView(favor: Favor){
            itemView.textFavor.text = favor.title
            itemView.textDetalles.text = favor.description
            itemView.karmaPoints.text = favor.karma.toString()+"K"
        }
    }

}