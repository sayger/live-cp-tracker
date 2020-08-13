package com.aditya.livecptracker.screens

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class ContestDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContestDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contest_details, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timeSetter: String = when (arguments?.getString("fragmentFlag")) {
            "0" -> arguments?.getString("contestEndTime").toString()
            else -> arguments?.getString("contestStartTime").toString()
        }

        binding.timeSetterText.text = when (arguments?.getString("fragmentFlag")) {
            "0" -> "Ends In"
            else -> "Starts In"
        }
        binding.contestName.text = arguments?.getString("contestName")
        binding.contestPlatform.setImageResource(
            when (arguments?.getString("contestPlatform")) {
                "CODECHEF" -> R.drawable.ic_cc
                "CODEFORCES" -> R.drawable.cf
                "HACKEREARTH" -> R.drawable.he
                "HACKERRANK" -> R.drawable.ic_hr
                else -> R.drawable.pltfrm_unv
            }
        )
        binding.visitContestPage.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_contestDetailsFragment_to_contestPageFragment,
                bundleOf("contestUrl" to arguments?.getString("contestUrl"))
            )
        }
        val sdf = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
        val timeDiff: Long = sdf.parse(convertToSimpleDateFormat(timeSetter))!!.time -
                sdf.parse(LocalDateTime.now().toString().replaceRange(10, 11, " "))!!.time
        println("timediff = ${TimeUnit.MILLISECONDS.toDays(timeDiff)}")
        val dayTimer = object : CountDownTimer(TimeUnit.MILLISECONDS.toMillis(timeDiff), 1000) {
            override fun onFinish() {
                println("Day timer complete")
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.hour.setText("${millisUntilFinished/1000}")
            }
        }
        dayTimer.start()
    }

    private fun convertToSimpleDateFormat(s: String): String {
        var d = ""
        d += s.substring(5, 7) + "-"
        d +=
            when (s.substring(8, 11)) {
                "Jan" -> "01-"
                "Feb" -> "02-"
                "Mar" -> "03-"
                "Apr" -> "04-"
                "May" -> "05-"
                "Jun" -> "06-"
                "Jul" -> "07-"
                "Aug" -> "08-"
                "Sep" -> "09-"
                "Oct" -> "10-"
                "Nov" -> "11-"
                else -> "12-"
            }

        d += s.substring(12, 16) + " "
        d += s.substring(17, 22) + ":00"
        return d
    }
}