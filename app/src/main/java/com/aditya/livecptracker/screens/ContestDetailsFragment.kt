package com.aditya.livecptracker.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.aditya.livecptracker.R
import com.aditya.livecptracker.databinding.FragmentContestDetailsBinding
import java.time.LocalDateTime

class ContestDetailsFragment : Fragment() {
    private lateinit var binding : FragmentContestDetailsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contest_details, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timeSetter: String = when(arguments?.getString("fragmentFlag")) {
            "0" -> arguments?.getString("contestEndTime").toString()
            else -> arguments?.getString("contestStartTime").toString()
        }
        binding.timeSetterText.text = when(arguments?.getString("fragmentFlag")) {
            "0" -> "Ends In"
            else -> "Starts In"
        }
        binding.contestName.text = arguments?.getString("contestName")
        binding.contestPlatform.setImageResource(
            when(arguments?.getString("contestPlatform")) {
                "CODECHEF" -> R.drawable.ic_cc
                "CODEFORCES" -> R.drawable.cf
                "HACKEREARTH" -> R.drawable.he
                "HACKERRANK" -> R.drawable.ic_hr
                else -> R.drawable.pltfrm_unv
            }
        )
        binding.visitContestPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_contestDetailsFragment_to_contestPageFragment,
            bundleOf("contestUrl" to arguments?.getString("contestUrl")))
        }
    }
}