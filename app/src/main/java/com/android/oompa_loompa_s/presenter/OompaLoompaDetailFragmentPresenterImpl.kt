package com.android.oompa_loompa_s.presenter

import com.android.oompa_loompa_s.core.CorePresenter
import com.android.oompa_loompa_s.view.OompaLompaDetail.impl.OompaLoompaDetailFragmentImpl

interface OompaLoompaDetailFragmentPresenterImpl :
    CorePresenter {
    fun setView(view: OompaLoompaDetailFragmentImpl)
    fun setId(id: Long)
}