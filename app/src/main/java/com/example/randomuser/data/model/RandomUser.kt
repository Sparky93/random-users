package com.example.randomuser.data.model

data class RandomUser(
    val name: Name,
    val location: Location,
    val dob: DateOfBirth,
    val picture: Picture
)

data class Name(val first: String, val last: String)

data class Location(val country: String, val timezone: Timezone)

data class Timezone(val offset: String, val description: String)

data class DateOfBirth(val date: String, val age: Int)

data class Picture(val large: String, val medium: String, val thumbnail: String)