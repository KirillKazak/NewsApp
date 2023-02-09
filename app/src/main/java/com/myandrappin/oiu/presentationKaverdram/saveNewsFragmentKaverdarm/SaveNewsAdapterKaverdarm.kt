package com.myandrappin.oiu.presentationKaverdram.saveNewsFragmentKaverdarm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myandrappin.oiu.R
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntitySaveNewsKaverdarm

class SaveNewsAdapterKaverdarm: RecyclerView.Adapter<SaveNewsAdapterKaverdarm.SaveNewsViewHolderKaverdarm>() {

    var onSaveNewsItemClickListenerKaverdarm: OnSaveNewsItemClickListenerKaverdarm? = null

    var listOfSaveNewsKaverdarm = listOf<DatabaseEntitySaveNewsKaverdarm>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveNewsViewHolderKaverdarm {
        val viewKaverdarm = LayoutInflater.from(parent.context)
            .inflate(R.layout.last_news_item_kaverdarm, parent, false)
        return SaveNewsViewHolderKaverdarm(viewKaverdarm)
    }

    override fun onBindViewHolder(holder: SaveNewsViewHolderKaverdarm, position: Int) {
        val itemSaveNewsKaverdarm = listOfSaveNewsKaverdarm[position]

        Glide.with(holder.ivLastNewsImgKaverdarm)
            .load(itemSaveNewsKaverdarm.imageKaverdarm)
            .placeholder(R.drawable.no_img_kaverdarm)
            .into(holder.ivLastNewsImgKaverdarm)

        holder.tvLastNewsTitleTrazyn.text = itemSaveNewsKaverdarm.titleKaverdarm
        holder.tvDescriptionTrazyn.text = itemSaveNewsKaverdarm.descriptionKaverdarm
        holder.tvLastNewsDateKaverdarm.text = itemSaveNewsKaverdarm.dateKaverdarm

        holder.btnReedMoreLastNewsKaverdarm.setOnClickListener {
            onSaveNewsItemClickListenerKaverdarm?.onReedMoreNewsBtnClickKaverdarm(
                itemSaveNewsKaverdarm.titleKaverdarm,
                itemSaveNewsKaverdarm.imageKaverdarm,
                itemSaveNewsKaverdarm.dateKaverdarm,
                itemSaveNewsKaverdarm.descriptionKaverdarm,
                itemSaveNewsKaverdarm.contentKaverdarm,
                false
            )
        }
        holder.btnBookmarkLastNewsKaverdarm.setBackgroundResource(R.drawable.icon_bookmark_kaverdarm)
    }

    override fun getItemCount(): Int = listOfSaveNewsKaverdarm.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class SaveNewsViewHolderKaverdarm(viewKaverdarm: View) :
        RecyclerView.ViewHolder(viewKaverdarm) {
        val ivLastNewsImgKaverdarm: ImageView = viewKaverdarm.findViewById(R.id.ivLastNewsImgKaverdarm)
        val tvLastNewsTitleTrazyn: TextView = viewKaverdarm.findViewById(R.id.tvLastNewsTitleKaverdam)
        val tvDescriptionTrazyn: TextView = viewKaverdarm.findViewById(R.id.tvDescriptionKaverdam)
        val tvLastNewsDateKaverdarm: TextView = viewKaverdarm.findViewById(R.id.tvLastNewsDateKaverdarm)
        val btnReedMoreLastNewsKaverdarm: AppCompatButton = viewKaverdarm.findViewById(R.id.btnReedMoreLastNewsKaverdarm)
        val btnBookmarkLastNewsKaverdarm: AppCompatButton = viewKaverdarm.findViewById(R.id.btnBookmarkLastNewsKaverdarm)
    }

    interface OnSaveNewsItemClickListenerKaverdarm {
        fun onReedMoreNewsBtnClickKaverdarm(
            titleKaverdram: String,
            imgKaverdram: String,
            dateKaverdram: String,
            descriptionKaverdram: String,
            contentKaverdram: String,
            bookmarkIsClickedKaverdram: Boolean)
    }
}