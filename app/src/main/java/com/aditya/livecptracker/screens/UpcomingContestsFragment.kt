package com.aditya.livecptracker.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aditya.livecptracker.R
import com.aditya.livecptracker.adapters.OngoingContestsAdapter
import com.aditya.livecptracker.adapters.UpcomingContestsAdapter
import com.aditya.livecptracker.databinding.FragmentUpcomingContestsBinding
import com.aditya.livecptracker.viewModels.UpcomingContestsViewModel
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import kotlinx.android.synthetic.main.fragment_ongoing_contests.*

class UpcomingContestsFragment : Fragment() {
    private lateinit var binding : FragmentUpcomingContestsBinding
    private lateinit var navController: NavController
    private val viewModel: UpcomingContestsViewModel by lazy {
        ViewModelProvider(this).get(UpcomingContestsViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_upcoming_contests, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.animationView.visibility = View.VISIBLE
        binding.fetchText.visibility = View.VISIBLE
        binding.radioButton.setOnCheckedChangeListener{ _, checkedId ->
            if(checkedId == R.id.ongoing_rb)
                navController.navigate(R.id.action_upcomingContestsFragment_to_ongoingContestsFragment)
        }
        val adapter = UpcomingContestsAdapter()
        recyclerView.adapter = adapter
        viewModel.upcomingContestData.observe(viewLifecycleOwner, Observer {
            adapter.setContestData(it)
            binding.animationView.visibility = View.GONE
            binding.fetchText.visibility = View.GONE
        })
        binding.dropDown.setOnClickListener {
            val popup = PopupMenu(requireContext(), it)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.dropdown_menu, popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.app_theme -> {
                        true
                    }
                    R.id.oss_lic -> {
                        startActivity(Intent(requireContext(), OssLicensesMenuActivity::class.java))
                        true
                    }
                    R.id.about_app -> {
                        startActivity(Intent(requireContext(), AboutAppActivity::class.java))
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        }
    }
}