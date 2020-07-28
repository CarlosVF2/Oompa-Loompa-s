package com.android.oompa_loompa_s.presenter

import com.android.oompa_loompa_s.core.CorePresenter
import com.android.oompa_loompa_s.view.OompaLompaList.impl.OompaLompaFragmentImpl

interface OompaLompaFragmentPresenter : CorePresenter{
    fun setView(mainActivity: OompaLompaFragmentImpl)
    fun loadMoreItems()
    fun filterData(query: String)
}