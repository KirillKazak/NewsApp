package com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm

import com.myandrappin.oiu.domainKaverdram.RepositoryKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import io.reactivex.Single

class GetLastTwentyNewsDataUseCaseKaverdram(private val repositoryKaverdarm: RepositoryKaverdarm) {

    fun getLastTwentyNewsDataKaverdram(): Single<ResponceModelKaverdarm> =
        repositoryKaverdarm.getLastTwentyNewsDataKaverdram()
}