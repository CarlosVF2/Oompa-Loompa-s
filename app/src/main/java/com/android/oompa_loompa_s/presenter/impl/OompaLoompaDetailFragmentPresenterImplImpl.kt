package com.android.oompa_loompa_s.presenter.impl

import com.android.oompa_loompa_s.core.impl.CorePresenterImpl
import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.presenter.OompaLoompaDetailFragmentPresenterImpl
import com.android.oompa_loompa_s.repository.OompaLompaDetailRepository
import com.android.oompa_loompa_s.view.OompaLompaDetail.impl.OompaLoompaDetailFragmentImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OompaLoompaDetailFragmentPresenterImplImpl @Inject constructor(private val repository: OompaLompaDetailRepository) :
    CorePresenterImpl(), OompaLoompaDetailFragmentPresenterImpl {

    private var responseOompaLoompa: Response<OompaLoompa>? = null
    private lateinit var oompaLoompaDetailFragment: OompaLoompaDetailFragmentImpl
    private var id: Long = 0L
    private lateinit var oompaLoompaDetail: OompaLoompa

    //region CorePresenter

    override fun onViewBinded() {
        this.oompaLoompaDetailFragment.onInitLoading()
        loadData()
    }

    override fun isLoadingFinish(): Boolean {
        return responseOompaLoompa!!.isSuccessful || messageError.isNotBlank()
    }

    override fun onDataLoaded() {
        if (isLoadingFinish()) {
            if(messageError.isNotBlank()){
                oompaLoompaDetailFragment.onLoadError(messageError)
                return
            }
            oompaLoompaDetail = responseOompaLoompa!!.body()!!
            loadDataInView()
            oompaLoompaDetailFragment.onLoaded()
        }
    }

    private fun loadDataInView() {
        oompaLoompaDetailFragment.setPpalImage(oompaLoompaDetail.image)
        oompaLoompaDetailFragment.setName(oompaLoompaDetail.firstName + " " + oompaLoompaDetail.lastName)
        oompaLoompaDetailFragment.setEdad(oompaLoompaDetail.age.toString())
        oompaLoompaDetailFragment.setPais(oompaLoompaDetail.country)
        oompaLoompaDetailFragment.setGenero(oompaLoompaDetail.gender)
        oompaLoompaDetailFragment.setEmail(oompaLoompaDetail.email)
        oompaLoompaDetailFragment.setProfesion(oompaLoompaDetail.profession)
        oompaLoompaDetailFragment.setColor(oompaLoompaDetail.favorite.color)
        oompaLoompaDetailFragment.setComida(oompaLoompaDetail.favorite.food)
        oompaLoompaDetailFragment.setCancion(oompaLoompaDetail.favorite.song)
        oompaLoompaDetailFragment.setRandomText(oompaLoompaDetail.favorite.random_string)
    }

    //endregion CorePresenter

    //region Methods interface

    override fun setView(view: OompaLoompaDetailFragmentImpl) {
        this.oompaLoompaDetailFragment = view
    }

    override fun setId(id: Long) {
        this.id = id
    }

    private fun loadData() {
        repository.getOompaLompaDetail(id).enqueue(object :
            Callback<OompaLoompa> {
            override fun onFailure(call: Call<OompaLoompa>?, t: Throwable?) {
                messageError = t!!.localizedMessage
                onDataLoaded()
            }

            override fun onResponse(
                call: Call<OompaLoompa>?,
                response: Response<OompaLoompa>?
            ) {
                responseOompaLoompa = response
                onDataLoaded()
            }

        })
    }
    //endregion Methods interface


}