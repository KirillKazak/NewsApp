package com.myandrappin.oiu.domainKaverdram

import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import io.reactivex.Single

interface RepositoryKaverdarm {
    fun getFiveHeadlinesDataKaverdram(): Single<ResponceModelKaverdarm>
    fun getLastTwentyNewsDataKaverdram(): Single<ResponceModelKaverdarm>
    fun getLastOneHundredNewsDataKaverdram(): Single<ResponceModelKaverdarm>
    fun getNewsByKeyWordDataKaverdram(keyWordKaverdram: String): Single<ResponceModelKaverdarm>
}