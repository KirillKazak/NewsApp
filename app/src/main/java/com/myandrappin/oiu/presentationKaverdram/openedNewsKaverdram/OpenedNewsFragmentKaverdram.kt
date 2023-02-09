package com.myandrappin.oiu.presentationKaverdram.openedNewsKaverdram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.myandrappin.oiu.R
import com.myandrappin.oiu.dataKaverdram.roomKaverdarm.DatabaseSaveNewsKaverdarm
import com.myandrappin.oiu.databinding.FragmentOpenedNewsKaverdramBinding
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntitySaveNewsKaverdarm
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram

const val TITLE_KAVERDARM = "TITLE_KAVERDARM"
const val IMG_KAVERDARM = "IMG_KAVERDARM"
const val DATE_KAVERDARM = "DATE_KAVERDARM"
const val DESCRIPTION_KAVERDARM = "DESCRIPTION_KAVERDARM"
const val CONTENT_KAVERDARM = "CONTENT_KAVERDARM"
const val BOOKMARK_IS_CLICKED_KAVERDARM = "BOOKMARK_IS_CLICKED_KAVERDARM"

class OpenedNewsFragmentKaverdram : Fragment() {
    private lateinit var vbOpenedNewsFragmentKaverdram: FragmentOpenedNewsKaverdramBinding
    private val databaseKaverdarm by lazy {
        Room.databaseBuilder(
            requireContext(), DatabaseSaveNewsKaverdarm::class.java,
            UtilKaverdram.roomDatabaseNameSaveKaverdram
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbOpenedNewsFragmentKaverdram = FragmentOpenedNewsKaverdramBinding.inflate(inflater, container, false)
        return vbOpenedNewsFragmentKaverdram.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleKaverdarm = arguments?.getString(TITLE_KAVERDARM)
        val dateKaverdarm = arguments?.getString(DATE_KAVERDARM)
        val contentKaverdarm = arguments?.getString(CONTENT_KAVERDARM)
        val descriptionKaverdarm = arguments?.getString(DESCRIPTION_KAVERDARM)
        val imageKaverdarm = arguments?.getString(IMG_KAVERDARM)
        val boolIsClickKaverdarm = arguments?.getBoolean(BOOKMARK_IS_CLICKED_KAVERDARM)

        var isSaveNewsKaverdarm = false
        for (i in databaseKaverdarm.listSavedNewsDaoKaverdarm().getAllSaveItemsKaverdarm()) {

            if (boolIsClickKaverdarm!!) {
                isSaveNewsKaverdarm = true
            } else if (titleKaverdarm == i.titleKaverdarm) {
                isSaveNewsKaverdarm = true
                break
            }

        }

        with(vbOpenedNewsFragmentKaverdram) {
            tvOpenedNewsTitleKaverdarm.text = titleKaverdarm
            tvOpenedNewsDateKaverdarm.text = dateKaverdarm
            tvOpenedNewsContentKaverdarm.text = contentKaverdarm

            Glide.with(requireContext())
                .load(imageKaverdarm)
                .centerCrop()
                .placeholder(R.drawable.no_img_kaverdarm)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivOpenedNewsImgKaverdarm)

            if (!isSaveNewsKaverdarm) {
                btnBookmarkOpenedNewsKaverdarm.isClickable = true
                btnBookmarkOpenedNewsKaverdarm.setBackgroundResource(R.drawable.read_later_no_active_caverdram)
                btnBookmarkOpenedNewsKaverdarm.setOnClickListener {
                    databaseKaverdarm.listSavedNewsDaoKaverdarm().addItemToSaveDatabaseKaverdarm(
                        DatabaseEntitySaveNewsKaverdarm(
                            titleKaverdarm!!, imageKaverdarm!!, dateKaverdarm!!,
                            descriptionKaverdarm!!, contentKaverdarm!!, isSaveNewsKaverdarm
                        )
                    )
                    btnBookmarkOpenedNewsKaverdarm.setBackgroundResource(R.drawable.read_later_active_caverdram)
                    btnBookmarkOpenedNewsKaverdarm.isClickable = false
                }
            } else {
                btnBookmarkOpenedNewsKaverdarm.isClickable = false
                btnBookmarkOpenedNewsKaverdarm.setBackgroundResource(R.drawable.read_later_active_caverdram)
            }

            btnGoBackOpenedNewsKaverdram.setOnClickListener {
                parentFragmentManager.beginTransaction().detach(this@OpenedNewsFragmentKaverdram).commit()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().detach( this@OpenedNewsFragmentKaverdram).commit()
            }
        })
    }

    companion object {
        fun newInstanceOpenedNewsFragmentKaverdram(
            titleKaverdram: String,
            imgKaverdram: String,
            dateKaverdram: String,
            descriptionKaverdram: String,
            contentKaverdram: String,
            bookmarkIsClickedKaverdram: Boolean
        ) =
            OpenedNewsFragmentKaverdram().apply {
                arguments = Bundle().apply {
                    putString(TITLE_KAVERDARM, titleKaverdram)
                    putString(IMG_KAVERDARM, imgKaverdram)
                    putString(DATE_KAVERDARM, dateKaverdram)
                    putString(DESCRIPTION_KAVERDARM, descriptionKaverdram)
                    putString(CONTENT_KAVERDARM, contentKaverdram)
                    putBoolean(BOOKMARK_IS_CLICKED_KAVERDARM, bookmarkIsClickedKaverdram)
                }
            }
    }
}