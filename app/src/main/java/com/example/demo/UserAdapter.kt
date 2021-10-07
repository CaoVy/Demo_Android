package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*


class UserAdapter(private val data: MutableList<UserData>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val views = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent,
            false)
        return MyViewHolder(views)
    }

    fun deleted(i: Int){
        data.removeAt(i)
        notifyDataSetChanged()
    }

    fun addItem(i: Int, datas:UserData){
        data.add(i, datas)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bindView(currentItem)
    }

    override fun getItemCount() = data.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(user: UserData){
            user.run {
                itemView.let {
                    it.imageview.setImageResource(imageViews)
                    it.textviewA.text = textNames
                }
            }
        }
    }
}
