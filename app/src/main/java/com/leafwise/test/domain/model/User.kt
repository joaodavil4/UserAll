package com.leafwise.test.domain.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val gender: String,
    val pictureURL: String,
    val phone: String,
    val birthday: String,
    val twitterHandle: String
)
