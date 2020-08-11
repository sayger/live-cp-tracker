package com.aditya.livecptracker.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aditya.livecptracker.R
import com.aditya.livecptracker.databinding.FragmentUpcomingContestsBinding

class UpcomingContestsFragment : Fragment() {
    private lateinit var binding : FragmentUpcomingContestsBinding
    private lateinit var navController: NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_upcoming_contests, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.radioButton.setOnCheckedChangeListener{ group, checkedId ->
            if(checkedId == R.id.ongoing_rb)
                navController.navigate(R.id.action_upcomingContestsFragment_to_ongoingContestsFragment)
        }
    }
}