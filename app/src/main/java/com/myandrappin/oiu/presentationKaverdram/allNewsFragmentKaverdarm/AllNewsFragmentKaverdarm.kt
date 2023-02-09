package com.myandrappin.oiu.presentationKaverdram.allNewsFragmentKaverdarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.myandrappin.oiu.R
import com.myandrappin.oiu.databinding.FragmentAllNewsKaverdarmBinding
import com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm.LastNewsAdapterKaverdarm
import com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm.NewsFragmentViewModelKaverdarm
import com.myandrappin.oiu.presentationKaverdram.openedNewsKaverdram.OpenedNewsFragmentKaverdram
import com.myandrappin.oiu.presentationKaverdram.searchNewsFragmentKaverdarm.SearchNewsFragmentKaverdarm

class AllNewsFragmentKaverdarm : Fragment() {
    private lateinit var vbAllNewsFragmentKaverdarm: FragmentAllNewsKaverdarmBinding
    private val vmNewsFragmentCaverdarm by lazy { ViewModelProvider(this)[NewsFragmentViewModelKaverdarm::class.java] }
    private val lastNewsAdapterCaverdarm by lazy { LastNewsAdapterKaverdarm(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbAllNewsFragmentKaverdarm = FragmentAllNewsKaverdarmBinding.inflate(inflater, container, false)
        return vbAllNewsFragmentKaverdarm.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmNewsFragmentCaverdarm.getLastOneHundredNewsDataKaverdram()
        vmNewsFragmentCaverdarm.lastTwentyNewsLiveDataKaverdarm.observe(viewLifecycleOwner) {
            lastNewsAdapterCaverdarm.listOfLastNewsKaverdarm = it.articlesKaverdarm
            vbAllNewsFragmentKaverdarm.recyclerViewAllNewsKaverdarm.adapter = lastNewsAdapterCaverdarm
        }

        lastNewsAdapterCaverdarm.onLastNewsItemClickListenerKaverdarm = object : LastNewsAdapterKaverdarm.OnLastNewsItemClickListenerKaverdarm {
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

            override fun onBookmarkBtnClickKaverdarm() {}
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction()
                    .detach(this@AllNewsFragmentKaverdarm)
                    .commit()
            }
        })

        vbAllNewsFragmentKaverdarm.btnGoBackAllNewsKaverdram.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .detach(this@AllNewsFragmentKaverdarm)
                .commit()
        }

        vbAllNewsFragmentKaverdarm.btnSearchAllNewsKaverdarm.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.containerKaverdarm, SearchNewsFragmentKaverdarm())
                .commit()
        }
    }

}