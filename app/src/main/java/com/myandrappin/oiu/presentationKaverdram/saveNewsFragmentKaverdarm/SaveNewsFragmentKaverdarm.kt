package com.myandrappin.oiu.presentationKaverdram.saveNewsFragmentKaverdarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.room.Room
import com.myandrappin.oiu.R
import com.myandrappin.oiu.dataKaverdram.roomKaverdarm.DatabaseSaveNewsKaverdarm
import com.myandrappin.oiu.databinding.FragmentSaveNewsKaverdarmBinding
import com.myandrappin.oiu.presentationKaverdram.openedNewsKaverdram.OpenedNewsFragmentKaverdram
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram

class SaveNewsFragmentKaverdarm : Fragment() {
    private lateinit var vbSaveNewsFragmentKaverdarm: FragmentSaveNewsKaverdarmBinding
    private val saveNewsAdapterKaverdarm by lazy { SaveNewsAdapterKaverdarm() }
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
        vbSaveNewsFragmentKaverdarm = FragmentSaveNewsKaverdarmBinding.inflate(inflater, container, false)
        return vbSaveNewsFragmentKaverdarm.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (databaseKaverdarm.listSavedNewsDaoKaverdarm().getAllSaveItemsKaverdarm().isEmpty()) {
            vbSaveNewsFragmentKaverdarm.tvYouDontSaveNewsKaverdarm.visibility = View.VISIBLE
        } else {
            vbSaveNewsFragmentKaverdarm.tvYouDontSaveNewsKaverdarm.visibility = View.INVISIBLE
            saveNewsAdapterKaverdarm.listOfSaveNewsKaverdarm = databaseKaverdarm.listSavedNewsDaoKaverdarm().getAllSaveItemsKaverdarm()
            vbSaveNewsFragmentKaverdarm.recyclerViewSaveNewsKaverdarm.adapter = saveNewsAdapterKaverdarm
        }

        saveNewsAdapterKaverdarm.onSaveNewsItemClickListenerKaverdarm = object : SaveNewsAdapterKaverdarm.OnSaveNewsItemClickListenerKaverdarm {
            override fun onReedMoreNewsBtnClickKaverdarm(
                titleKaverdram: String,
                imgKaverdram: String,
                dateKaverdram: String,
                descriptionKaverdram: String,
                contentKaverdram: String,
                bookmarkIsClickedKaverdram: Boolean
            ) {
                parentFragmentManager.beginTransaction()
                    .add(R.id.containerKaverdarm,
                        OpenedNewsFragmentKaverdram.newInstanceOpenedNewsFragmentKaverdram(
                            titleKaverdram,
                            imgKaverdram,
                            dateKaverdram,
                            descriptionKaverdram,
                            contentKaverdram,
                            bookmarkIsClickedKaverdram
                        )
                    )
                    .commit()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction()
                    .detach(this@SaveNewsFragmentKaverdarm)
                    .commit()
            }
        })

        vbSaveNewsFragmentKaverdarm.btnGoBackSaveNewsKaverdram.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .detach(this@SaveNewsFragmentKaverdarm)
                .commit()
        }
    }
}