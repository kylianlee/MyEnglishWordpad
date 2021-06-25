package com.example.englishwordpadassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.englishwordpadassignment.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var rdb: DatabaseReference
    lateinit var rdb1: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        rdb = FirebaseDatabase.getInstance().getReference("MyData/word")
//        initData()
//        initData1()
        init()
    }
/*
    private fun initData1() {
        val scan = Scanner(resources.openRawResource(R.raw.words))
        var num = 0;
        val count = 50;
        var i = 0;
        while (i < count) {
            num++
            i++
            rdb1 = FirebaseDatabase.getInstance().getReference("DailyData/${num}")
            while (scan.hasNextLine()) {
                val word = scan.nextLine()
                val meaning = scan.nextLine()
                val item = MyData(
                    word.toString(),
                    meaning.toString(),
                    isSelected = false,
                    isChecked = false
                )
                rdb.child(word.toString()).setValue(item)
            }
        }
        scan.close()
    }
*/
    private fun initData() {
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while (scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            val item = MyData(word.toString(), meaning.toString(), isSelected = false, isChecked = false)
            rdb.child(word.toString()).setValue(item)
        }
        scan.close()
    }

    private fun init() {
        val fragment = supportFragmentManager.beginTransaction()
        val imgFragment = MainFragment()
        fragment.replace(R.id.framelayout, imgFragment)
        fragment.commit()
    }
}