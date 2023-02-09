package com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.myandrappin.oiu.R
import com.myandrappin.oiu.dataKaverdram.roomKaverdarm.DatabaseSaveNewsKaverdarm
import com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm.ArticleKaverdam
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram

class HeadlineAdapterKaverdarm(private val contextKaverdram: Context): RecyclerView.Adapter<HeadlineAdapterKaverdarm.HeadlineViewHolderKaverdarm>() {
    var onHeadlineItemClickListenerKaverdarm: OnHeadlineItemClickListenerKaverdarm? = null
    private val databaseKaverdarm by lazy {
        Room.databaseBuilder(contextKaverdram, DatabaseSaveNewsKaverdarm::class.java,
            UtilKaverdram.roomDatabaseNameSaveKaverdram
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    var listOfHeadlinesKaverdarm = listOf<ArticleKaverdam>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolderKaverdarm {
        val viewKaverdarm = LayoutInflater.from(parent.context).inflate(R.layout.headline_item_kaverdarm, parent, false)
        return HeadlineViewHolderKaverdarm(viewKaverdarm)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolderKaverdarm, position: Int) {
        val itemHeadlineKaverdarm = listOfHeadlinesKaverdarm[position]

        Glide.with(holder.ivHeadlineImgKaverdarm)
            .load(itemHeadlineKaverdarm.urlToImageKaverdam)
            .placeholder(R.drawable.no_img_kaverdarm)
            .into(holder.ivHeadlineImgKaverdarm)

        holder.tvHeadlineTitleKaverdarm.text = itemHeadlineKaverdarm.titleKaverdam
        holder.tvHeadlineDateKaverdarm.text = itemHeadlineKaverdarm.publishedAtKaverdam.split("T")[0]

        val contentKaverdarm = if(itemHeadlineKaverdarm.contentKaverdam == null) {
            itemHeadlineKaverdarm.descriptionKaverdam ?: ""
        } else {
            itemHeadlineKaverdarm.contentKaverdam.split("[")[0]
        }

        var isSaveNewsKaverdarm = false
        for (i in databaseKaverdarm.listSavedNewsDaoKaverdarm().getAllSaveItemsKaverdarm()) {
            if (itemHeadlineKaverdarm.titleKaverdam != i.titleKaverdarm) {
                isSaveNewsKaverdarm = false
            } else {
                isSaveNewsKaverdarm = true
                break
            }
        }

        holder.headlineItemCLKaverdarm.setOnClickListener {
            onHeadlineItemClickListenerKaverdarm?.onHeadlineItemClickKaverdarm(
                itemHeadlineKaverdarm.titleKaverdam ?: "",
                itemHeadlineKaverdarm.urlToImageKaverdam ?: "",
                itemHeadlineKaverdarm.publishedAtKaverdam.split("T")[0],
                itemHeadlineKaverdarm.descriptionKaverdam ?: "",
                contentKaverdarm,
                isSaveNewsKaverdarm
            )
        }
    }

    override fun getItemCount(): Int = listOfHeadlinesKaverdarm.size

    class HeadlineViewHolderKaverdarm(viewKaverdarm: View): RecyclerView.ViewHolder(viewKaverdarm) {
        val ivHeadlineImgKaverdarm: ImageView = viewKaverdarm.findViewById(R.id.ivHeadlineImgKaverdarm)
        val tvHeadlineTitleKaverdarm: TextView = viewKaverdarm.findViewById(R.id.tvHeadlineTitleKaverdarm)
        val tvHeadlineDateKaverdarm: TextView = viewKaverdarm.findViewById(R.id.tvHeadlineDateKaverdarm)
        val headlineItemCLKaverdarm: ConstraintLayout = viewKaverdarm.findViewById(R.id.headlineItemCLKaverdarm)
    }

    interface OnHeadlineItemClickListenerKaverdarm {
        fun onHeadlineItemClickKaverdarm(
            titleKaverdram: String,
            imgKaverdram: String,
            dateKaverdram: String,
            descriptionKaverdram: String,
            contentKaverdram: String,
            bookmarkIsClickedKaverdram: Boolean
        )
    }

}