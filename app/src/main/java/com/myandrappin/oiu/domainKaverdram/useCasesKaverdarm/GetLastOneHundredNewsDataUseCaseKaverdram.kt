package com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm

import com.myandrappin.oiu.domainKaverdram.RepositoryKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import io.reactivex.Single

class GetLastOneHundredNewsDataUseCaseKaverdram(private val repositoryKaverdarm: RepositoryKaverdarm) {
    fun getLastOneHundredNewsDataKaverdram(): Single<ResponceModelKaverdarm> =
        repositoryKaverdarm.getLastOneHundredNewsDataKaverdram()
}