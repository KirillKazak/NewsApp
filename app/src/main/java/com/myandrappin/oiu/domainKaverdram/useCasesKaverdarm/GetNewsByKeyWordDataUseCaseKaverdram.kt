package com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm

import com.myandrappin.oiu.domainKaverdram.RepositoryKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import io.reactivex.Single

class GetNewsByKeyWordDataUseCaseKaverdram(private val repositoryKaverdarm: RepositoryKaverdarm) {
    fun getNewsByKeyWordDataKaverdram(keyWordKaverdram: String): Single<ResponceModelKaverdarm> =
        repositoryKaverdarm.getNewsByKeyWordDataKaverdram(keyWordKaverdram)
}