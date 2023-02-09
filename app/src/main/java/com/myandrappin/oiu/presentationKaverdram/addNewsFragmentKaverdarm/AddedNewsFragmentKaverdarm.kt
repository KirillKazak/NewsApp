package com.myandrappin.oiu.presentationKaverdram.addNewsFragmentKaverdarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.room.Room
import com.myandrappin.oiu.R
import com.myandrappin.oiu.dataKaverdram.roomKaverdarm.DatabaseAddNewsKaverdarm
import com.myandrappin.oiu.databinding.FragmentAddedNewsKaverdarmBinding
import com.myandrappin.oiu.presentationKaverdram.openedNewsKaverdram.OpenedNewsFragmentKaverdram
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram

class AddedNewsFragmentKaverdarm : Fragment() {
    private lateinit var vbAddNewsFragmentKaverdarm: FragmentAddedNewsKaverdarmBinding
    private val databaseAddedNewsKaverdarm by lazy {
        Room.databaseBuilder(
            requireContext(), DatabaseAddNewsKaverdarm::class.java,
            UtilKaverdram.roomDatabaseNameAddedKaverdram
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val addedNewsAdapterCaverdarm by lazy { AddedNewsAdapterKaverdarm() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbAddNewsFragmentKaverdarm = FragmentAddedNewsKaverdarmBinding.inflate(inflater, container, false)
        return vbAddNewsFragmentKaverdarm.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (databaseAddedNewsKaverdarm.listAddedNewsDaoKaverdarm().getAllAddItemsKaverdarm().isEmpty()) {
            vbAddNewsFragmentKaverdarm.tvYouDontAddNewsKaverdarm.visibility = View.VISIBLE
        } else {
            vbAddNewsFragmentKaverdarm.tvYouDontAddNewsKaverdarm.visibility = View.INVISIBLE
            addedNewsAdapterCaverdarm.listOfAddedNewsKaverdarm = databaseAddedNewsKaverdarm.listAddedNewsDaoKaverdarm().getAllAddItemsKaverdarm()
            vbAddNewsFragmentKaverdarm.recyclerViewMyNewsKaverdarm.adapter = addedNewsAdapterCaverdarm
        }

        addedNewsAdapterCaverdarm.onAddedNewsItemClickListenerKaverdarm = object : AddedNewsAdapterKaverdarm.OnAddedNewsItemClickListenerKaverdarm {
            override fun onReedMoreAddedNewsBtnClickKaverdarm(
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

        vbAddNewsFragmentKaverdarm.btnAddedNewsKaverdarm.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.containerKaverdarm,
                    AddNewsFragmentKaverdarm()
                )
                .commit()
        }

        vbAddNewsFragmentKaverdarm.btnGoBackAddedNewsKaverdram.setOnClickListener {
            parentFragmentManager.beginTransaction().detach(this@AddedNewsFragmentKaverdarm).commit()
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().detach( this@AddedNewsFragmentKaverdarm).commit()
            }
        })
    }
}