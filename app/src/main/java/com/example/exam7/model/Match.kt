package com.example.exam7.model

data class Match(
    val team1: Team?,
    val team2: Team?,
    val matchTime: Float?,
    val matchDate: Long?,
    val stadiumAdress: String?,
    val matchSummary: MatchSummary?
)
