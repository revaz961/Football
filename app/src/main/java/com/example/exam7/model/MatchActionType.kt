package com.example.exam7.model

import com.example.exam7.R

enum class MatchActionType {
    GOAL {
         val title: String
            get() = "Goals by"
         val image: Int
            get() = R.drawable.path_44
    },
    YELLO_CARD {
         val title: String
            get() = "Tripping"
         val image: Int
            get() = R.drawable.path_81
    },
    RED_CARD {
         val title: String
            get() = "Tripping"
         val image: Int
            get() = R.drawable.path_81
    },
    SUBSTITUTION {
        val title: String
            get() = TODO("Not yet implemented")
        val image: Int
            get() = TODO("Not yet implemented")
    }
}