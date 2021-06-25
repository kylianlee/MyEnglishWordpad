package com.example.englishwordpadassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.englishwordpadassignment.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    var binding: FragmentMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        init()
        return binding!!.root
    }

    private fun init() {
        val fragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.addToBackStack(null)
        binding!!.apply {
            allWords.setOnClickListener {
                val allWordFragment = AllWordsFragment()
                fragment.replace(R.id.framelayout, allWordFragment)
                fragment.commit()
            }
            randomWords.setOnClickListener {
                val dailyWordsFragment = DailyWordsFragment()
                fragment.replace(R.id.framelayout, dailyWordsFragment)
                fragment.commit()
            }
            favoriteWord.setOnClickListener {
                val favoriteWordsFragment = FavoriteWordsFragment()
                fragment.replace(R.id.framelayout, favoriteWordsFragment)
                fragment.commit()
            }
        }
    }

}