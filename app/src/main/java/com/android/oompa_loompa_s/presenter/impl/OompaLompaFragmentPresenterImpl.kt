package com.android.oompa_loompa_s.presenter.impl

import com.android.oompa_loompa_s.core.impl.CorePresenterImpl
import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.model.OompaLoompaResponse
import com.android.oompa_loompa_s.presenter.OompaLompaFragmentPresenter
import com.android.oompa_loompa_s.repository.OompaLompaRepository
import com.android.oompa_loompa_s.view.OompaLompaList.impl.OompaLompaFragmentImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OompaLompaFragmentPresenterImpl @Inject constructor(private val repository: OompaLompaRepository) : CorePresenterImpl(), OompaLompaFragmentPresenter {

    private lateinit var responseOompaLoompa: Response<OompaLoompaResponse>
    private lateinit var oompaLompaFragment: OompaLompaFragmentImpl
    private var data: MutableList<OompaLoompa> = ArrayList()
    private var page: Long = 1

    //region CorePresenter

    override fun onViewBinded() {
        oompaLompaFragment.onInitLoading()
        loadData()
    }

    override fun isLoadingFinish(): Boolean {
        return messageError.isNotBlank() || responseOompaLoompa!!.isSuccessful
    }

    override fun onDataLoaded() {
        if(isLoadingFinish()){
            if(messageError.isNotBlank()){
                oompaLompaFragment.onLoadError(messageError)
                return
            }
            oompaLompaFragment.hideLoadinProgress()
            oompaLompaFragment.setData(data)
            oompaLompaFragment.onLoaded()
        }
    }

    //endregion CorePresenter

    //region Methods interface

    override fun setView(mainActivity: OompaLompaFragmentImpl) {
        oompaLompaFragment = mainActivity
    }

    private fun loadData() {
        oompaLompaFragment.closeSearchView()
        repository.getOompaLompa(page).enqueue(object :
            Callback<OompaLoompaResponse> {
            override fun onFailure(call: Call<OompaLoompaResponse>?, t: Throwable?) {
                messageError = t!!.localizedMessage
                onDataLoaded()
            }

            override fun onResponse(
                call: Call<OompaLoompaResponse>?,
                response: Response<OompaLoompaResponse>
            ) {
                responseOompaLoompa = response
                var listado : MutableList<OompaLoompa> = (response.body() as OompaLoompaResponse).oompaLoompas
                if(data.isEmpty()) {
                    data = listado
                } else if(!data.containsAll(listado)){
                    data.addAll((response.body() as OompaLoompaResponse).oompaLoompas)
                }
                onDataLoaded()
            }

        })
    }

    override fun loadMoreItems() {
        oompaLompaFragment.showLoadingProgress()
        page++
        loadData()
    }

    override fun filterData(query: String) {
        if(query.isBlank()){
            page = 1
            loadData()
            return
        }
        data = repository.filterMemoryData(query, data)
        oompaLompaFragment.filterData(data)
    }

    //endregion Methods interface




}