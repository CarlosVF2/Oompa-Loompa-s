package com.android.oompa_loompa_s.dagger

import com.android.oompa_loompa_s.presenter.OompaLompaFragmentPresenter
import com.android.oompa_loompa_s.presenter.OompaLoompaDetailFragmentPresenterImpl
import com.android.oompa_loompa_s.presenter.impl.OompaLompaFragmentPresenterImpl
import com.android.oompa_loompa_s.presenter.impl.OompaLoompaDetailFragmentPresenterImplImpl
import com.android.oompa_loompa_s.repository.OompaLompaDetailRepository
import com.android.oompa_loompa_s.repository.OompaLompaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideOompaLompaFragmentPresenter(api: OompaLompaRepository): OompaLompaFragmentPresenter =
        OompaLompaFragmentPresenterImpl(api)

    @Provides
    @Singleton
    fun provideOompaLoompaDetailFragmentPresenter(api: OompaLompaDetailRepository): OompaLoompaDetailFragmentPresenterImpl =
        OompaLoompaDetailFragmentPresenterImplImpl(api)
}