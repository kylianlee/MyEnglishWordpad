package com.example.englishwordpadassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwordpadassignment.databinding.FragmentAllWordsBinding
import com.example.englishwordpadassignment.databinding.FragmentDailyWordsBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DailyWordsFragment : Fragment() {
    lateinit var binding : FragmentDailyWordsBinding
    lateinit var adapter: MyAdapter
    lateinit var rdb: DatabaseReference
    lateinit var fw: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var options: FirebaseRecyclerOptions<MyData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDailyWordsBinding.inflate(layoutInflater,container, false)
        rdb = FirebaseDatabase.getInstance().getReference("MyData/dailyWord")
        fw = FirebaseDatabase.getInstance().getReference("MyData/favoriteWord")
        init()
        return binding!!.root
    }

    private fun init() {
        recyclerView = binding.recyclerView
        val query = rdb
        options = FirebaseRecyclerOptions.Builder<MyData>()
            .setQuery(query, MyData::class.java)
            .build()
        adapter = MyAdapter(options)
        adapter.itemClickListener = object :MyAdapter.OnItemClickListener{
            override fun OnItemClick(holder: MyAdapter.ViewHolder, view: View, position: Int) {
                holder.word.setOnClickListener {
                    if (holder.meaning.visibility == View.GONE) {
                        holder.meaning.visibility = View.VISIBLE
                        rdb.child(holder.binding.word.text.toString())
                            .child("selected")
                            .setValue(true)
                    } else {
                        holder.meaning.visibility = View.GONE
                        rdb.child(holder.binding.word.text.toString())
                            .child("selected")
                            .setValue(false)
                    }
                }
                holder.meaning.setOnClickListener {
                    holder.meaning.visibility = View.GONE
                    rdb.child(holder.binding.word.text.toString())
                        .child("selected")
                        .setValue(false)
                }
                holder.check.setOnClickListener {
                    if(holder.check.isChecked) {
                        rdb.child(holder.binding.word.text.toString())
                            .child("checked")
                            .setValue(true)
                        fw.child(holder.binding.word.text.toString())
                            .setValue(MyData(holder.word.text.toString(), holder.meaning.text.toString(), false, true))
                        Toast.makeText(context, "즐겨찾기에 추가", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        rdb.child(holder.binding.word.text.toString())
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