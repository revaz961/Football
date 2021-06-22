package com.example.exam7.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam7.R
import com.example.exam7.adapter.MatchAdapter
import com.example.exam7.databinding.FragmentMainBinding
import com.example.exam7.extension.load
import com.example.exam7.extension.toDateFormat
import com.example.exam7.model.FootballMatch
import com.example.exam7.model.Summary
import com.example.exam7.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class MatchFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    FragmentMainBinding::inflate,
    MainViewModel::class.java
) {

    private lateinit var adapter: MatchAdapter

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

    private fun initView(model: FootballMatch) {

        with(binding) {

            val match = model.match!!

            tvDate.text = match.matchDate?.toDateFormat("d MMMM yyyy")

            tvStadium.text = match.stadiumAdress

            val time = "${match.matchTime}'"
            tvTime.text = time

            ivTeam1.load(match.team1?.teamImage!!)
            ivTeam2.load(match.team2?.teamImage!!)

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
        adapter = MatchAdapter()
        adapter.setItems(list)

        binding.rvMatch.adapter = adapter
        binding.rvMatch.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observes() {
        viewModel.livedata.observe(this, {
            initView(it)
            initRecycler(it.match!!.matchSummary!!.summaries!!)
        })
    }
}