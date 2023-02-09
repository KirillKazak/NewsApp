package com.myandrappin.oiu.utilKaverdram

import android.util.Base64
import androidx.lifecycle.MutableLiveData

class UtilKaverdram {
    companion object {
        const val apiTokenKaverdram = "c4f46fb02c9b41ea84d676c0b568bb2e"
        val apiLinkKaverdram = String(Base64.decode("aHR0cHM6Ly9uZXdzYXBpLm9yZw==", Base64.DEFAULT))
        const val kaverdram = "Kaverdram"
        val internetConnectionStateLiveDataKaverdram = MutableLiveData<InternetConnectionStateKaverdarm>()

        const val roomDatabaseNameSaveKaverdram = "roomDatabaseNameSaveKaverdram"
        const val roomDatabaseNameAddedKaverdram = "roomDatabaseNameAddedKaverdram"
        const val roomSaveTableNameKaverdram = "roomSaveTableNameKaverdram"
        const val roomAddTableNameKaverdram = "roomAddTableNameKaverdram"
    }
}