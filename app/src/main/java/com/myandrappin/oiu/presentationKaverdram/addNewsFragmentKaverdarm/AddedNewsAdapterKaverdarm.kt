package com.myandrappin.oiu.presentationKaverdram.addNewsFragmentKaverdarm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.myandrappin.oiu.R
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntityAddNewsKaverdarm

class AddedNewsAdapterKaverdarm: RecyclerView.Adapter<AddedNewsAdapterKaverdarm.AddedNewsViewHolderKaverdarm>()  {
    var onAddedNewsItemClickListenerKaverdarm: OnAddedNewsItemClickListenerKaverdarm? = null

    var listOfAddedNewsKaverdarm = listOf<DatabaseEntityAddNewsKaverdarm>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedNewsViewHolderKaverdarm {
        val viewKaverdarm = LayoutInflater.from(parent.context)
            .inflate(R.layout.your_news_item_kaverdarm, parent, false)
        return AddedNewsViewHolderKaverdarm(viewKaverdarm)
    }

    override fun onBindViewHolder(holder: AddedNewsViewHolderKaverdarm, position: Int) {
        val itemAddedNewsKaverdarm = listOfAddedNewsKaverdarm[position]

        Glide.with(holder.ivYourNewsImgKaverdarm)
            .load(itemAddedNewsKaverdarm.imageKaverdarm)
            .centerCrop()
            .placeholder(R.drawable.no_img_kaverdarm)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivYourNewsImgKaverdarm)
        holder.tvYourNewsTitleTrazyn.text = itemAddedNewsKaverdarm.titleKaverdarm
        holder.tvYourDescriptionTrazyn.text = itemAddedNewsKaverdarm.descriptionKaverdarm

        holder.btnReedMoreYourNewsKaverdarm.setOnClickListener {
            onAddedNewsItemClickListenerKaverdarm?.onReedMoreAddedNewsBtnClickKaverdarm(
                itemAddedNewsKaverdarm.titleKaverdarm,
                itemAddedNewsKaverdarm.imageKaverdarm,
                "Moderação",
                itemAddedNewsKaverdarm.descriptionKaverdarm,
                itemAddedNewsKaverdarm.contentKaverdarm,
                true
            )
        }
    }

    override fun getItemCount(): Int = listOfAddedNewsKaverdarm.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class AddedNewsViewHolderKaverdarm(viewKaverdarm: View) :
        RecyclerView.ViewHolder(viewKaverdarm) {
        val ivYourNewsImgKaverdarm: ImageView = viewKaverdarm.findViewById(R.id.ivYourNewsImgKaverdarm)
        val tvYourNewsTitleTrazyn: TextView = viewKaverdarm.findViewById(R.id.tvYourNewsTitleKaverdam)
        val tvYourDescriptionTrazyn: TextView = viewKaverdarm.findViewById(R.id.tvYourDescriptionKaverdam)
        val btnReedMoreYourNewsKaverdarm: AppCompatButton = viewKaverdarm.findViewById(R.id.btnReedMoreYourNewsKaverdarm)
    }

    interface OnAddedNewsItemClickListenerKaverdarm {
        fun onReedMoreAddedNewsBtnClickKaverdarm(
            titleKaverdram: String,
            imgKaverdram: String,
            dateKaverdram: String,
            descriptionKaverdram: String,
            contentKaverdram: String,
            bookmarkIsClickedKaverdram: Boolean
        )
    }
}