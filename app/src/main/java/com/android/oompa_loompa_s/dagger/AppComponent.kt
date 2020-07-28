package com.android.oompa_loompa_s.dagger

import com.android.oompa_loompa_s.view.OompaLompaDetail.impl.OompaLoompaDetailFragmentImpl
import com.android.oompa_loompa_s.view.OompaLompaList.impl.OompaLompaFragmentImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(mainFragment: OompaLompaFragmentImpl)
    fun inject(mainFragment: OompaLoompaDetailFragmentImpl)
}