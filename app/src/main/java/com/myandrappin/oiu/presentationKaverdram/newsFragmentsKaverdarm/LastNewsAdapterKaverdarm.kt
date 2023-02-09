package com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.myandrappin.oiu.R
import com.myandrappin.oiu.dataKaverdram.roomKaverdarm.DatabaseSaveNewsKaverdarm
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntitySaveNewsKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ArticleKaverdam
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.roomDatabaseNameSaveKaverdram

class LastNewsAdapterKaverdarm(private val contextKaverdram: Context): RecyclerView.Adapter<LastNewsAdapterKaverdarm.LastNewsViewHolderKaverdarm>() {
    private val databaseKaverdarm by lazy {
        Room.databaseBuilder(contextKaverdram, DatabaseSaveNewsKaverdarm::class.java, roomDatabaseNameSaveKaverdram)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    var onLastNewsItemClickListenerKaverdarm: OnLastNewsItemClickListenerKaverdarm? = null

    var listOfLastNewsKaverdarm = listOf<ArticleKaverdam>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastNewsViewHolderKaverdarm {
        val viewKaverdarm = LayoutInflater.from(parent.context)
            .inflate(R.layout.last_news_item_kaverdarm, parent, false)
        return LastNewsViewHolderKaverdarm(viewKaverdarm)
    }

    override fun onBindViewHolder(holder: LastNewsViewHolderKaverdarm, position: Int) {
        val itemLastNewsKaverdarm = listOfLastNewsKaverdarm[position]

        Glide.with(holder.ivLastNewsImgKaverdarm)
            .load(itemLastNewsKaverdarm.urlToImageKaverdam)
            .placeholder(R.drawable.no_img_kaverdarm)
            .into(holder.ivLastNewsImgKaverdarm)

        holder.tvLastNewsTitleKaverdam.text = itemLastNewsKaverdarm.titleKaverdam
        holder.tvDescriptionKaverdam.text = itemLastNewsKaverdarm.descriptionKaverdam
        holder.tvLastNewsDateKaverdarm.text = itemLastNewsKaverdarm.publishedAtKaverdam.split("T")[0]

        val contentKaverdarm = if(itemLastNewsKaverdarm.contentKaverdam == null) {
            itemLastNewsKaverdarm.descriptionKaverdam ?: ""
        } else {
            itemLastNewsKaverdarm.contentKaverdam.split("[")[0]
        }

        var isSaveNewsKaverdarm = false
        for (i in databaseKaverdarm.listSavedNewsDaoKaverdarm().getAllSaveItemsKaverdarm()) {
            if (itemLastNewsKaverdarm.titleKaverdam != i.titleKaverdarm) {
                isSaveNewsKaverdarm = false
                holder.btnBookmarkLastNewsKaverdarm.setBackgroundResource(R.drawable.btn_bookmarl_last_news_kaverdarm)
                holder.btnBookmarkLastNewsKaverdarm.isClickable = true
            } else {
                isSaveNewsKaverdarm = true
                holder.btnBookmarkLastNewsKaverdarm.setBackgroundResource(R.drawable.icon_bookmark_kaverdarm)
                holder.btnBookmarkLastNewsKaverdarm.isClickable = false
                break
            }
        }

        holder.btnReedMoreLastNewsKaverdarm.setOnClickListener {
            Log.d("itemLastNewsKaverdarm1", contentKaverdarm)
            Log.d("itemLastNewsKaverdarm2", itemLastNewsKaverdarm.descriptionKaverdam ?:  "")
            Log.d("itemLastNewsKaverdarm3", itemLastNewsKaverdarm.titleKaverdam ?:  "")
            Log.d("itemLastNewsKaverdarm4", itemLastNewsKaverdarm.urlToImageKaverdam ?:  "")
            Log.d("itemLastNewsKaverdarm5", itemLastNewsKaverdarm.publishedAtKaverdam ?:  "")
            onLastNewsItemClickListenerKaverdarm?.onReedMoreNewsBtnClickKaverdarm(
                itemLastNewsKaverdarm.titleKaverdam ?: "",
                itemLastNewsKaverdarm.urlToImageKaverdam ?: "",
                itemLastNewsKaverdarm.publishedAtKaverdam.split("T")[0],
                itemLastNewsKaverdarm.descriptionKaverdam ?: "",
                contentKaverdarm,
                isSaveNewsKaverdarm
            )
        }

        holder.btnBookmarkLastNewsKaverdarm.setOnClickListener {
            databaseKaverdarm.listSavedNewsDaoKaverdarm().addItemToSaveDatabaseKaverdarm(
                DatabaseEntitySaveNewsKaverdarm(
                    itemLastNewsKaverdarm.titleKaverdam,
                    itemLastNewsKaverdarm.urlToImageKaverdam,
                    itemLastNewsKaverdarm.publishedAtKaverdam.split("T")[0],
                    itemLastNewsKaverdarm.descriptionKaverdam ?: "",
                    contentKaverdarm,
                    isSaveNewsKaverdarm
                )
            )
            onLastNewsItemClickListenerKaverdarm?.onBookmarkBtnClickKaverdarm()
            holder.btnBookmarkLastNewsKaverdarm.setBackgroundResource(R.drawable.icon_bookmark_kaverdarm)
            holder.btnBookmarkLastNewsKaverdarm.isClickable = false
        }
    }

    override fun getItemCount(): Int = listOfLastNewsKaverdarm.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class LastNewsViewHolderKaverdarm(viewKaverdarm: View) :
        RecyclerView.ViewHolder(viewKaverdarm) {
        val ivLastNewsImgKaverdarm: ImageView = viewKaverdarm.findViewById(R.id.ivLastNewsImgKaverdarm)
        val tvLastNewsTitleKaverdam: TextView = viewKaverdarm.findViewById(R.id.tvLastNewsTitleKaverdam)
        val tvDescriptionKaverdam: TextView = viewKaverdarm.findViewById(R.id.tvDescriptionKaverdam)
        val tvLastNewsDateKaverdarm: TextView = viewKaverdarm.findViewById(R.id.tvLastNewsDateKaverdarm)
        val btnReedMoreLastNewsKaverdarm: AppCompatButton = viewKaverdarm.findViewById(R.id.btnReedMoreLastNewsKaverdarm)
        val btnBookmarkLastNewsKaverdarm: AppCompatButton = viewKaverdarm.findViewById(R.id.btnBookmarkLastNewsKaverdarm)
    }

    interface OnLastNewsItemClickListenerKaverdarm {
        fun onReedMoreNewsBtnClickKaverdarm(
            titleKaverdram: String,
            imgKaverdram: String,
            dateKaverdram: String,
            descriptionKaverdram: String,
            contentKaverdram: String,
            bookmarkIsClickedKaverdram: Boolean
        )

        fun onBookmarkBtnClickKaverdarm()
    }
}