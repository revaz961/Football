package com.example.exam7.model

import com.google.gson.annotations.SerializedName

data class Action(
    @SerializedName("player", alternate = ["player1"])
    val player1: Player?,
    val player2: Player?,
    val goalType: Int?
)