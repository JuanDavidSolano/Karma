package com.example.karma.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.model.Favor
import kotlinx.android.synthetic.main.favs_layout.view.*
import kotlinx.android.synthetic.main.list_layout.view.*

class PersonalFavorsAdapter (private val context: Context, var clickListener: onFavorClickListener): RecyclerView.Adapter<PersonalFavorsAdapter.PersonalFavsViewHolder>(){
    private var dataList = mutableListOf<Favor>()

    fun setListData(data:MutableList<Favor>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalFavorsAdapter.PersonalFavsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favs_layout, parent, false)
        return PersonalFavsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(dataList.size>0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: PersonalFavorsAdapter.PersonalFavsViewHolder, position: Int) {
        val favor = dataList[position]
        holder.bindView(favor, clickListener)
    }

    inner class PersonalFavsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bindView(favor: Favor, action: onFavorClickListener){
            itemView.textTitulo.text = favor.title
            itemView.textEstado.text = "Estado: "+favor.status
            itemView.setOnClickListener{
                action.onItemClick(favor, adapterPosition)
            }
        }
    }

    interface onFavorClickListener{
        fun onItemClick(item: Favor, position:Int){

        }
    }
}