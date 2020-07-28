package com.android.oompa_loompa_s.core

interface CoreFragment {
    fun onInitLoading()
    fun onLoaded()
    fun onLoadError(error: String)
}