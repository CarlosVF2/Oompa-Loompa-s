package com.android.oompa_loompa_s.interactor

import com.android.oompa_loompa_s.model.OompaLoompa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor : Callback<OompaLoompa> {

    var isFinished: Boolean = false

    override fun onFailure(call: Call<OompaLoompa>, t: Throwable) {
        isFinished = true

    }

    override fun onResponse(call: Call<OompaLoompa>, response: Response<OompaLoompa>) {
        isFinished = true

        TODO("Not yet implemented")
    }
}