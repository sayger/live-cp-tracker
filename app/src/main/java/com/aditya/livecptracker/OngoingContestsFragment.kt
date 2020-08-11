package com.aditya.livecptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aditya.livecptracker.databinding.FragmentOngoingContestsBinding

class OngoingContestsFragment : Fragment() {
    private lateinit var binding : FragmentOngoingContestsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_contests, container, false)
        return binding.root
    }
}