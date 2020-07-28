package com.android.oompa_loompa_s.repository

import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.network.OompaLoompaApi
import retrofit2.Call
import retrofit2.Retrofit

class OompaLompaDetailRepository(private val retrofit: Retrofit) {
    fun getOompaLompaDetail(id: Long) : Call<OompaLoompa>{
        return retrofit.create(OompaLoompaApi::class.java).getOompaLoompaDetail(id)
    }
}