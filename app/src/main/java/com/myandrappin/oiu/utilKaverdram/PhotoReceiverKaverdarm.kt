package com.myandrappin.oiu.utilKaverdram

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.kaverdram
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

interface PhotoReceiverKaverdarm {
    companion object {
        var uriKaverdarm: Uri = Uri.EMPTY
    }

    fun onReciverExistKaverdarm(intentcapKaverdarm: Intent, actKaverdarm: AppCompatActivity, actreslauncherKaverdarm: ActivityResultLauncher<Intent>) {
        val dateFormatKaverdarm = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val externalFilesKaverdarm = actKaverdarm.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val adderCategoryKaverdarm = Intent(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE).setType("image/*")
        val fileKaverdarm = File.createTempFile("${dateFormatKaverdarm}_$kaverdram", ".jpg", externalFilesKaverdarm)

        uriKaverdarm = FileProvider.getUriForFile(actKaverdarm, "${actKaverdarm.packageName}.provider", fileKaverdarm)
        intentcapKaverdarm.putExtra(MediaStore.EXTRA_OUTPUT, uriKaverdarm)

        actreslauncherKaverdarm.launch(
            Intent(Intent.ACTION_CHOOSER)
                .putExtra(Intent.EXTRA_INTENT, intentcapKaverdarm)
                .putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(adderCategoryKaverdarm))
        )
    }
}