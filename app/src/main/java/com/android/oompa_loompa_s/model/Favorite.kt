package com.android.oompa_loompa_s.model

import com.google.gson.annotations.SerializedName

data class Favorite(@SerializedName("color")val color: String,
                    @SerializedName("food") val food :String,
                    @SerializedName("random_string") val random_string : String,
                    @SerializedName("song")val song: String)
