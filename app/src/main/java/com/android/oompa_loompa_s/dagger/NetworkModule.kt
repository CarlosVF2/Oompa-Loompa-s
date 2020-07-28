
package com.android.oompa_loompa_s.dagger

import com.android.oompa_loompa_s.network.OompaLoompaApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideUserApi(retrofit: Retrofit): OompaLoompaApi {
    return retrofit.create(OompaLoompaApi::class.java)
  }

  //companion object {
  //  private const val NAME_BASE_URL = "NAME_BASE_URL"
  //}
//
  //@Provides
  //@Named(NAME_BASE_URL)
  //fun provideBaseUrlString() = " "
//
  //@Provides
  //@Singleton
  //fun provideHttpClient() = OkHttpClient()
//
  //@Provides
  //@Singleton
  //fun provideRequestBuilder(@Named(NAME_BASE_URL) baseUrl: String) =
  //    baseUrl.toHttpUrlOrNull()!!.newBuilder()
//
  //@Provides
  //@Singleton
  //fun provideoOompaLoompaApi(client: OkHttpClient, requestBuilder: HttpUrl.Builder) = OompaLoompaApi2(client, requestBuilder)
}