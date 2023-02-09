package com.myandrappin.oiu.dataKaverdram.retrofitKaverdram

import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.apiLinkKaverdram
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigureRetrofitKaverdarm {
    val apiKaverdarm: ApiKaverdram by lazy {
        retrofitKaverdarm.create(ApiKaverdram::class.java)
    }

    private val retrofitKaverdarm by lazy {
        val httpLoggingInterceptorKaverdarm = HttpLoggingInterceptor()
        httpLoggingInterceptorKaverdarm.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientKaverdarm = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptorKaverdarm)
            .build()

        Retrofit.Builder()
            .baseUrl(apiLinkKaverdram)
            .client(okHttpClientKaverdarm)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}