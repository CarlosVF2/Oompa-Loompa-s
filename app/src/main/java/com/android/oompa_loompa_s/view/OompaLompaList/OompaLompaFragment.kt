package com.android.oompa_loompa_s.view.OompaLompaList

import com.android.oompa_loompa_s.core.CoreFragment
import com.android.oompa_loompa_s.model.OompaLoompa

interface OompaLompaFragment : CoreFragment {
    fun showLoadingProgress()
    fun hideLoadinProgress()
    fun closeSearchView()
    fun filterData(data: MutableList<OompaLoompa>)
}