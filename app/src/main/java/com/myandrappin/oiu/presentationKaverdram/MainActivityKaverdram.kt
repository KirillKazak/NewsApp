package com.myandrappin.oiu.presentationKaverdram

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.lifecycle.lifecycleScope
import com.myandrappin.oiu.databinding.ActivityMainKaverdramBinding
import com.myandrappin.oiu.utilKaverdram.*
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.internetConnectionStateLiveDataKaverdram
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityKaverdram : AppCompatActivity(), PhotoReceiverKaverdarm {
    private lateinit var vbMainActivityKaverdram: ActivityMainKaverdramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vbMainActivityKaverdram = ActivityMainKaverdramBinding.inflate(layoutInflater)
        setContentView(vbMainActivityKaverdram.root)
        replaceLayoutsKaverdram()
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun replaceLayoutsKaverdram() {
        internetConnectionStateLiveDataKaverdram.observe(this) {
            if (it == InternetConnectionStateKaverdarm.INTERNETSTATESUCCESSKAVERDRAM) {
                vbMainActivityKaverdram.connectionErrorLayoutKaverdram.visibility = View.INVISIBLE
                lifecycleScope.launch {
                    delay(2000)
                    startActivity(Intent(this@MainActivityKaverdram, NewsActivityKavedarm::class.java))
                    finish()
                }
            } else if (it == InternetConnectionStateKaverdarm.INTERNETTATEFAILKAVERDRAM){
                vbMainActivityKaverdram.connectionErrorLayoutKaverdram.visibility = View.VISIBLE
            }
        }
    }
}