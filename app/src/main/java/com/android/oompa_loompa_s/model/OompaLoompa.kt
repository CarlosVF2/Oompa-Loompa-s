package com.android.oompa_loompa_s.model

import com.google.gson.annotations.SerializedName

data class OompaLoompa(@SerializedName("age") val age: Long,
                       @SerializedName("country") val country: String,
                       @SerializedName("email") val email: String,
                       @SerializedName("favorite") val favorite: Favorite,
                       @SerializedName("first_name") val firstName: String,
                       @SerializedName("gender") val gender: String,
                       @SerializedName("height") val height: String,
                       @SerializedName("id") val id: Long,
                       @SerializedName("image") val image: String,
                       @SerializedName("last_name") val lastName: String,
                       @SerializedName("profession") val profession: String) {
}