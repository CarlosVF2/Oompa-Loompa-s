package com.android.oompa_loompa_s.model

import com.google.gson.annotations.SerializedName

data class OompaLoompaResponse(@SerializedName("current") var current:Long, @SerializedName("results") var oompaLoompas:MutableList<OompaLoompa>, @SerializedName("total") var total:Long)
