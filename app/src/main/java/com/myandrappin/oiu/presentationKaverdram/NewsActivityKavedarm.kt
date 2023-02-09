package com.myandrappin.oiu.presentationKaverdram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myandrappin.oiu.databinding.ActivityNewsKavedarmBinding
import com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm.NewsFragmentKaverdarm

class NewsActivityKavedarm : AppCompatActivity() {
    private lateinit var vbNewsActivityKavedarm: ActivityNewsKavedarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vbNewsActivityKavedarm = ActivityNewsKavedarmBinding.inflate(layoutInflater)
        setContentView(vbNewsActivityKavedarm.root)

        supportFragmentManager.beginTransaction().replace(vbNewsActivityKavedarm.containerKaverdarm.id, NewsFragmentKaverdarm()).commit()
    }
}