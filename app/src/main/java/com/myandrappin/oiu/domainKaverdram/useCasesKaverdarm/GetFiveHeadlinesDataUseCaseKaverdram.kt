package com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm

import com.myandrappin.oiu.domainKaverdram.RepositoryKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import io.reactivex.Single

class GetFiveHeadlinesDataUseCaseKaverdram(private val repositoryKaverdarm: RepositoryKaverdarm) {
    fun getFiveHeadlinesDataKaverdram() : Single<ResponceModelKaverdarm> =
        repositoryKaverdarm.getFiveHeadlinesDataKaverdram()

}