package com.android.oompa_loompa_s.core

interface CorePresenter {
    fun onViewBinded()
    fun isLoadingFinish(): Boolean
    fun onDataLoaded()
}