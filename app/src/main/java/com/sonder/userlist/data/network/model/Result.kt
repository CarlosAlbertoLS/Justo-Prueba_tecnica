package com.sonder.userlist.data.network.model

data class Result(
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val name: Name,
    val phone: String,
    val picture: Picture,
)