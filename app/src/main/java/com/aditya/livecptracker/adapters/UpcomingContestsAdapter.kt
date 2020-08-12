package com.aditya.livecptracker.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aditya.livecptracker.R
import com.aditya.livecptracker.network.ContestDetails
import kotlinx.android.synthetic.main.item_view_upcoming.view.*
import java.util.*
import kotlin.collections.ArrayList

class UpcomingContestsAdapter() : RecyclerView.Adapter<UpcomingContestsAdapter.ViewHolder>() {
    private var contestList: List<ContestDetails> = ArrayList()
    fun setContestData(contestList: List<ContestDetails>) {
        this.contestList = contestList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val unitView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_upcoming, parent, false)
        return ViewHolder(unitView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contestList[position]
        holder.contestName.text = currentItem.name
        if (currentItem.name.substring(0, 1) == "\n") {
            var s = currentItem.name.removePrefix("\n")
            s = s.removeSuffix("\n")
            holder.contestName.text = s
        }
        /*
        * Host Date Format : Tue, 30 Mar 2021 00:00"
        */
        holder.contestMonth.text = currentItem.endTime.substring(8, 11)
        holder.contestDay.text = currentItem.endTime.substring(0, 3)
        holder.contestDate.text = currentItem.endTime.substring(5, 7)
        var contestPlatform = currentItem.platform.toLowerCase(Locale.ROOT)
        contestPlatform = contestPlatform.substring(0, 1).capitalize(Locale.ROOT)
            .plus(contestPlatform.subSequence(1, contestPlatform.length))
        holder.contestBrief.text = contestPlatform.plus(" | ${currentItem.duration}")
        holder.contestCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_upcomingContestsFragment_to_contestDetailsFragment,
                bundleOf("contestPlatform" to currentItem.platform,
                    "contestName" to currentItem.name, "contestStartTime" to currentItem.startTime)
            )
        }
    }

    override fun getItemCount(): Int = contestList.size
    class ViewHolder(unitView: View) : RecyclerView.ViewHolder(unitView) {
        val contestDate: TextView = unitView.date
        val contestDay: TextView = unitView.day
        val contestName: TextView = unitView.contest_name
        val contestBrief: TextView = unitView.contest_brief
        val contestMonth: TextView = unitView.month
        val contestCard: CardView = unitView.contest_card
    }
}