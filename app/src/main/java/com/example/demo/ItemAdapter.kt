package com.example.demo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_booking.view.*

class ItemAdapter(private val itemData: MutableList<ItemData>) :
    RecyclerView.Adapter<ItemAdapter.ItemMyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_booking, parent,
            false
        )
        return ItemMyViewHolder(view)
    }

    fun delete(item: Int){
        itemData.removeAt(item)
        notifyDataSetChanged()
    }

    fun addItem(item: Int, items: ItemData){
        itemData.add(item, items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemMyViewHolder, position: Int) {
        val currentItem = itemData[position]
        holder.bindData(currentItem)
    }

    override fun getItemCount() = itemData.size

    inner class ItemMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: ItemData) {
            item.run {
                itemView.let {
                    it.imageMayBay.setImageResource(image_view)
                    it.editText.text = texts
                    it.editTextA.text = textss
                    it.editTextB.text = textsx
                }
            }
             itemView.setOnClickListener {
                 val intent = Intent(itemView.context, MainActivity::class.java)
                 itemView.context.startActivity(intent)
                 Toast.makeText(itemView.context, "Ok Nhe",Toast.LENGTH_SHORT).show()
             }
        }
    }
}
