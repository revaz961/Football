package com.example.exam7.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam7.R
import com.example.exam7.adapter.MatchAdapter
import com.example.exam7.databinding.FragmentMainBinding
import com.example.exam7.extension.load
import com.example.exam7.extension.toDateFormat
import com.example.exam7.model.FootballMatch
import com.example.exam7.model.Match
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


    private fun initRecycler(match: Match) {
        binding.rvMatch.adapter = MatchAdapter().apply {
            getMatchInfo = { match }
            addItems(match.matchSummary?.summaries!!)
        }
        binding.rvMatch.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observes() {
        viewModel.livedata.observe(this, {
            initRecycler(it.match!!)
        })
    }
}