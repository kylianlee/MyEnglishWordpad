package com.example.englishwordpadassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwordpadassignment.databinding.RowBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class MyAdapter(options: FirebaseRecyclerOptions<MyData>): FirebaseRecyclerAdapter<MyData, MyAdapter.ViewHolder>(options) {
    interface OnItemClickListener{
        fun OnItemClick(holder: MyAdapter.ViewHolder, view: View, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null
    inner class ViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root){
        val check = binding.imageButton
        val linearLayout = binding.linearLayout
        val word = binding.word
        val meaning = binding.meaning
        init {
            binding.linearLayout.setOnClickListener {
                itemClickListener!!.OnItemClick(this, it, adapterPosition)
            }
        }
    }

//
//    fun moveItem(oldPos:Int, newPos:Int){
//        val item =items[oldPos]
//        items.removeAt(oldPos)
//        items.add(newPos, item)
//        notifyItemMoved(oldPos, newPos)
//    }
//
//    fun removeItem(pos:Int){
//        items.removeAt(pos)
//        notifyItemRemoved(pos)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        holder.binding.apply{
//            textView2.text =items[position].word
//            textView3.text =items[position].meaning
//            if(items[position].isSelected)
//                textView3.visibility = View.VISIBLE
//            else
//                textView3.visibility = View.GONE
//        }
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MyData) {
        holder.binding.apply{
            word.text =model.word
            meaning.text =model.meaning
            if(model.isSelected)
                meaning.visibility = View.VISIBLE
            else
                meaning.visibility = View.GONE
            if(model.isChecked)
                imageButton!!.isChecked = true
            else
                imageButton.isChecked = false
        }
    }

    override fun getItemViewType(position: Int) = position

}