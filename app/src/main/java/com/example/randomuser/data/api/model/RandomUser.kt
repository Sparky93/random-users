package com.example.randomuser.data.api.model

data class RandomUser(
    val name: Name,
    val location: Location,
    val dateOfBirth: DateOfBirth
)

data class Name(val first: String, val last: String)

data class Location(val country: String)

data class DateOfBirth(val date: String, val age: Int)

