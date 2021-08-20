package ru.skillbranch.gameofthrones.data.remote.responses

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("aliases")
    var aliases: List<String>,
    @SerializedName("allegiances")
    var allegiances: List<String>,
    @SerializedName("born")
    var born: String,
    @SerializedName("died")
    var died: String,
    @SerializedName("father")
    var father: String,
    @SerializedName("mother")
    var mother: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("titles")
    var titles: List<String>,
    @SerializedName("url")
    var url: String,
)
