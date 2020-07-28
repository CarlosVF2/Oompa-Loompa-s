package com.android.oompa_loompa_s.network

import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.model.OompaLoompaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaApi {

    @GET("?")
    fun getOompaLoompa(@Query("page") page: Long): Call<OompaLoompaResponse>

    @GET("{id}")
    fun getOompaLoompaDetail(@Path("id") id: Long): Call<OompaLoompa>

}