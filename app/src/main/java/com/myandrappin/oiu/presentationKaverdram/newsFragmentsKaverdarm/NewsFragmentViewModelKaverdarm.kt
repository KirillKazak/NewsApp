package com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myandrappin.oiu.dataKaverdram.RepositoryImplKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ResponceModelKaverdarm
import com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm.GetFiveHeadlinesDataUseCaseKaverdram
import com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm.GetLastOneHundredNewsDataUseCaseKaverdram
import com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm.GetLastTwentyNewsDataUseCaseKaverdram
import com.myandrappin.oiu.domainKaverdram.useCasesKaverdarm.GetNewsByKeyWordDataUseCaseKaverdram
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsFragmentViewModelKaverdarm: ViewModel()  {
    val headlinesLiveDataKaverdarm: MutableLiveData<ResponceModelKaverdarm> = MutableLiveData()
    val lastTwentyNewsLiveDataKaverdarm: MutableLiveData<ResponceModelKaverdarm> = MutableLiveData()
    private val repositoryImplKaverdarm =  RepositoryImplKaverdarm()
    private val getFiveHeadlinesDataUseCaseKaverdram = GetFiveHeadlinesDataUseCaseKaverdram(repositoryImplKaverdarm)
    private val getLastTwentyNewsDataUseCaseKaverdram = GetLastTwentyNewsDataUseCaseKaverdram(repositoryImplKaverdarm)
    private val getNewsByKeyWordDataUseCaseKaverdram = GetNewsByKeyWordDataUseCaseKaverdram(repositoryImplKaverdarm)
    private val getLastOneHundredNewsDataUseCaseKaverdram = GetLastOneHundredNewsDataUseCaseKaverdram(repositoryImplKaverdarm)
    private val compositDisposableKaverdram = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositDisposableKaverdram.dispose()
    }

    fun getFiveHeadlinesDataKaverdram() {
        compositDisposableKaverdram.add(
            getFiveHeadlinesDataUseCaseKaverdram.getFiveHeadlinesDataKaverdram()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    headlinesLiveDataKaverdarm.postValue(it)
                }, { })
        )
    }

    fun getLastTwentyNewsDataKaverdram() {
        compositDisposableKaverdram.add(
            getLastTwentyNewsDataUseCaseKaverdram.getLastTwentyNewsDataKaverdram()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    lastTwentyNewsLiveDataKaverdarm.postValue(it)
                }, { })
        )
    }

    fun getNewsByKeyWordDataKaverdram(keyWordKaverdram: String) {
        compositDisposableKaverdram.add(
            getNewsByKeyWordDataUseCaseKaverdram.getNewsByKeyWordDataKaverdram(keyWordKaverdram)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    lastTwentyNewsLiveDataKaverdarm.postValue(it)
                }, { })
        )
    }

    fun getLastOneHundredNewsDataKaverdram() {
        compositDisposableKaverdram.add(
            getLastOneHundredNewsDataUseCaseKaverdram.getLastOneHundredNewsDataKaverdram()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    lastTwentyNewsLiveDataKaverdarm.postValue(it)
                }, { })
        )
    }
}