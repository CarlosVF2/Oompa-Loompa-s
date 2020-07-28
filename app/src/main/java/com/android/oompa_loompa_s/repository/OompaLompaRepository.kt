package com.android.oompa_loompa_s.repository

import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.model.OompaLoompaResponse
import com.android.oompa_loompa_s.network.OompaLoompaApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Retrofit
import java.util.Locale.filter

class OompaLompaRepository(val retrofit: Retrofit) {
    fun getOompaLompa(page: Long) : Call<OompaLoompaResponse> {
        return retrofit.create(OompaLoompaApi::class.java).getOompaLoompa(page)
    }

    fun filterMemoryData(query: String, data: MutableList<OompaLoompa>) : MutableList<OompaLoompa> {
        val filterData = data.filter { e -> e.profession.contains(query) }
        return filterData.toMutableList()
    }

}