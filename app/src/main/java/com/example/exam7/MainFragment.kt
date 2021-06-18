package com.example.exam7

import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam7.adapter.ParentAdapter
import com.example.exam7.databinding.FragmentMainBinding
import com.example.exam7.extension.load
import com.example.exam7.fragment.BaseFragment
import com.example.exam7.model.MainModel
import com.example.exam7.model.Summary
import com.example.exam7.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    FragmentMainBinding::inflate,
    MainViewModel::class.java
) {

    private lateinit var adapter: ParentAdapter

    override fun start() {
        init()
    }

    private fun init() {
        observes()
        initBottomNavigation()
        viewModel.getMatch()
    }

    private fun initBottomNavigation() {
        binding.bnNavigate.getOrCreateBadge(R.id.navigation_favorites).apply {
            isVisible = true
            number = 3
        }
    }

    private fun initView(model: MainModel) {
        d("model", model.toString())
        with(binding) {

            val match = model.match!!

            tvDate.text = getDate(match.matchDate!!)

            tvStadium.text = match.stadiumAdress

            val time = "${match.matchTime}'"
            tvTime.text = time

            ivTeam1.load(match.team1?.teamImage!!, R.drawable.dinamo, R.drawable.dinamo)
            ivTeam2.load(match.team2?.teamImage!!, R.drawable.group_61, R.drawable.group_61)

            tvTeam1.text = match.team1.teamName
            tvTeam2.text = match.team2.teamName

            val score = "${match.team1.score} : ${match.team2.score}"
            tvScore.text = score

            tvTime.text = match.matchTime.toString()

            tvBallPossession1.text = match.team1.ballPosition.toString()
            tvBallPossession2.text = match.team2.ballPosition.toString()

            progress.progress = match.team1.ballPosition!!

            val halfScore = "${match.team1.score} : ${match.team2.score}"
            tvHalfScore.text = halfScore
        }
    }

    private fun initRecycler(list: List<Summary>) {
        adapter = ParentAdapter()
        adapter.setItems(list)

        binding.rvMatch.adapter = adapter
        binding.rvMatch.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getDate(millisecond: Long): String {
        val date = Date(millisecond)
        val format = SimpleDateFormat("d MMMM yyyy")
        return format.format(date)
    }

    private fun observes() {
        viewModel.livedata.observe(this, {
            initView(it)
            initRecycler(it.match!!.matchSummary!!.summaries!!)
        })
    }
}