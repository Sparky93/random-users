package com.example.randomuser.data.api.response

import com.example.randomuser.data.api.model.RandomUser

data class GetRandomUsersResponse(
    val results: List<RandomUser>
)