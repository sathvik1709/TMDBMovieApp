package com.sathvik1709.tmdbsamsungtest.di

import com.sathvik1709.tmdbsamsungtest.network.TMDBApiService
import com.sathvik1709.tmdbsamsungtest.repo.CloudRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideTMDBApiService(retrofit: Retrofit): TMDBApiService {
        var tmdbApiService: TMDBApiService = retrofit.create(TMDBApiService::class.java)
        return tmdbApiService
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun providesCloudRepository(tmdbApiService: TMDBApiService) = CloudRepository(tmdbApiService)

}