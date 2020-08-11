package com.aditya.livecptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aditya.livecptracker.databinding.FragmentOngoingContestsBinding

class OngoingContestsFragment : Fragment() {
    private lateinit var binding : FragmentOngoingContestsBinding
    private lateinit var navController: NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_contests, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.radioButton.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.upcoming_rb)
                navController.navigate(R.id.action_ongoingContestsFragment_to_upcomingContestsFragment)
        }
    }
}