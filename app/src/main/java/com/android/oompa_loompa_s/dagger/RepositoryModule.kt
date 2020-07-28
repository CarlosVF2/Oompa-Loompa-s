package com.android.oompa_loompa_s.dagger


import com.android.oompa_loompa_s.repository.OompaLompaDetailRepository
import com.android.oompa_loompa_s.repository.OompaLompaRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOompaLompaRepository(api : Retrofit): OompaLompaRepository =
        OompaLompaRepository(api)

    @Provides
    @Singleton
    fun provideOompaLoompaDetailRepository(api : Retrofit): OompaLompaDetailRepository =
        OompaLompaDetailRepository(api)
}