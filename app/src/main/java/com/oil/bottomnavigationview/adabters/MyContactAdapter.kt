package com.oil.bottomnavigationview.adabters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oil.bottomnavigationview.databinding.ItemRvBinding
import com.oil.bottomnavigationview.db.MyContact

class MyContactAdapter(val list: ArrayList<MyContact>, var rvAction: RvAction): RecyclerView.Adapter<MyContactAdapter.Vh>() {
    inner class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myContact: MyContact, position:Int){
            itemRvBinding.tvName.text=myContact.name
            itemRvBinding.tvNumber.text=myContact.number
            itemRvBinding.root.setOnLongClickListener {
                rvAction.deleteContact(myContact,position)

                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface RvAction{
        fun deleteContact(contact: MyContact,position: Int)
    }
}