package com.myandrappin.oiu

import android.app.Application
import android.net.ConnectivityManager
import com.myandrappin.oiu.utilKaverdram.InternetConnectionStateKaverdarm
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.internetConnectionStateLiveDataKaverdram
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ApplicationKaverdarm: Application() {
    private var internetStateKaverdarm =
        InternetConnectionStateKaverdarm.INTERNETSTATENOTEFOUNDKAVERDRAM

    override fun onCreate() {
        super.onCreate()


        internetConnectionStateLiveDataKaverdram.value = InternetConnectionStateKaverdarm.INTERNETSTATENOTEFOUNDKAVERDRAM
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                internetStateKaverdarm = if (checkInternetConnectionKaverdram()) {
                    InternetConnectionStateKaverdarm.INTERNETSTATESUCCESSKAVERDRAM
                } else {
                    InternetConnectionStateKaverdarm.INTERNETTATEFAILKAVERDRAM
                }

                if(internetConnectionStateLiveDataKaverdram.value != internetStateKaverdarm) {
                    internetConnectionStateLiveDataKaverdram.value = internetStateKaverdarm
                }
            }
        }
    }

    private fun  checkInternetConnectionKaverdram() =
        (getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isAvailable
            ?: false
}