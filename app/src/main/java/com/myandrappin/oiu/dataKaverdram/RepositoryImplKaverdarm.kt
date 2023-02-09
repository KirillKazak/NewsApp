package com.myandrappin.oiu.dataKaverdram

import com.myandrappin.oiu.dataKaverdram.retrofitKaverdram.ConfigureRetrofitKaverdarm
import com.myandrappin.oiu.domainKaverdram.RepositoryKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import io.reactivex.Single

class RepositoryImplKaverdarm: RepositoryKaverdarm {

    override fun getFiveHeadlinesDataKaverdram(): Single<ResponceModelKaverdarm> =
        ConfigureRetrofitKaverdarm().apiKaverdarm.getFiveHeadlinesDataKaverdram()

    override fun getLastTwentyNewsDataKaverdram(): Single<ResponceModelKaverdarm> =
        ConfigureRetrofitKaverdarm().apiKaverdarm.getLastTwentyNewsDataKaverdram()

    override fun getLastOneHundredNewsDataKaverdram(): Single<ResponceModelKaverdarm> =
        ConfigureRetrofitKaverdarm().apiKaverdarm.getLastOneHundredNewsDataKaverdram()

    override fun getNewsByKeyWordDataKaverdram(keyWordKaverdram: String): Single<ResponceModelKaverdarm> =
        ConfigureRetrofitKaverdarm().apiKaverdarm.getNewsByKeyWordsDataKaverdram(keyWordKaverdram)
}