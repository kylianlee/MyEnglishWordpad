package com.example.englishwordpadassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwordpadassignment.databinding.FragmentDailyWordsBinding
import com.example.englishwordpadassignment.databinding.FragmentFavoriteWordsBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FavoriteWordsFragment : Fragment() {
    lateinit var binding : FragmentFavoriteWordsBinding
    lateinit var adapter: MyAdapter
    lateinit var fw: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var options: FirebaseRecyclerOptions<MyData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteWordsBinding.inflate(layoutInflater,container, false)
        fw = FirebaseDatabase.getInstance().getReference("MyData/favoriteWord")
        init()
        return binding!!.root
    }

    private fun init() {
        recyclerView = binding.recyclerView
        val query = fw
        options = FirebaseRecyclerOptions.Builder<MyData>()
            .setQuery(query, MyData::class.java)
            .build()
        adapter = MyAdapter(options)
        adapter.itemClickListener = object :MyAdapter.OnItemClickListener{
            override fun OnItemClick(holder: MyAdapter.ViewHolder, view: View, position: Int) {
                holder.word.setOnClickListener {
                    if (holder.meaning.visibility == View.GONE) {
                        holder.meaning.visibility = View.VISIBLE
                        fw.child(holder.binding.word.text.toString())
                            .child("selected")
                            .setValue(true)
                    } else {
                        holder.meaning.visibility = View.GONE
                        fw.child(holder.binding.word.text.toString())
                            .child("selected")
                            .setValue(false)
                    }
                }
                holder.meaning.setOnClickListener {
                    holder.meaning.visibility = View.GONE
                    fw.child(holder.binding.word.text.toString())
                        .child("selected")
                        .setValue(false)
                }
                holder.check.setOnClickListener {
                    if(!holder.check.isChecked) {
                        fw.child(holder.binding.word.text.toString())
                            .child("checked")
                            .setValue(false)
                        fw.child(holder.binding.word.text.toString())
                            .removeValue()
                        Toast.makeText(context, "즐겨찾기에서 삭제", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        recyclerView.adapter = adapter
        adapter.startListening()
    }
}