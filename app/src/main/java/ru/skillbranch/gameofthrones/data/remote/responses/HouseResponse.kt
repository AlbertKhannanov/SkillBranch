package ru.skillbranch.gameofthrones.data.remote.responses

import com.google.gson.annotations.SerializedName

data class HouseResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("swornMembers")
    var swornMembers: List<String>,
    @SerializedName("words")
    var words: String
)
