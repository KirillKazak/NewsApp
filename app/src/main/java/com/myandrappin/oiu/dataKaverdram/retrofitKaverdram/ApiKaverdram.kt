package com.myandrappin.oiu.dataKaverdram.retrofitKaverdram

import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.apiTokenKaverdram
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiKaverdram {
    @GET("/v2/top-headlines?country=pt&category=sports&pageSize=5&apiKey=$apiTokenKaverdram")
    fun getFiveHeadlinesDataKaverdram(): Single<ResponceModelKaverdarm>

    @GET("/v2/top-headlines?country=pt&category=sports&pageSize=20&apiKey=$apiTokenKaverdram")
    fun getLastTwentyNewsDataKaverdram(): Single<ResponceModelKaverdarm>

    @GET("/v2/top-headlines?country=pt&category=sports&pageSize=100&apiKey=$apiTokenKaverdram")
    fun getLastOneHundredNewsDataKaverdram(): Single<ResponceModelKaverdarm>

    @GET("/v2/top-headlines?country=pt&category=sports&pageSize=20&apiKey=$apiTokenKaverdram")
    fun getNewsByKeyWordsDataKaverdram(
        @Query("q")
        keyWordKaverdram: String = ""
    ): Single<ResponceModelKaverdarm>
}