package com.dicoding.subs_lazyadityarn.model

import java.io.Serializable

data class Band (
    val id: Int,
    val name: String,
    val photoUrl: String,
    val genre: String,
    val album: String,
    val descrip: String
) : Serializable