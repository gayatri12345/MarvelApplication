package com.sample.data.di

import com.sample.data.BuildConfig
import com.sample.data.api.CharacterApi
import com.sample.data.utils.getHashKey
import com.sample.data.utils.getTimeStamp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This class is for Retrofit
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        builder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val hash = getHashKey()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(TIMESTAMP, getTimeStamp().toString())
                .addQueryParameter(API_KEY, BuildConfig.APP_PUBLIC_KEY)
                .addQueryParameter(HASH_KEY, hash)
                .build()
            val requestBuilder = original.newBuilder().url(url)
            chain.proceed(requestBuilder.build())

        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(getOkHttpClient()).build()
    }

    fun createCharacterApi(): CharacterApi {
        val retrofit = getRetrofitInstance()
        return retrofit.create(CharacterApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com"
        private const val TIMESTAMP = "ts"
        private const val API_KEY = "apikey"
        private const val HASH_KEY = "hash"
        private const val TIMEOUT = 30L
    }
}
