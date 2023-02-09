package com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.myandrappin.oiu.R
import com.myandrappin.oiu.databinding.FragmentNewsCaverdarmBinding
import com.myandrappin.oiu.presentationKaverdram.addNewsFragmentKaverdarm.AddedNewsFragmentKaverdarm
import com.myandrappin.oiu.presentationKaverdram.allNewsFragmentKaverdarm.AllNewsFragmentKaverdarm
import com.myandrappin.oiu.presentationKaverdram.openedNewsKaverdram.OpenedNewsFragmentKaverdram.Companion.newInstanceOpenedNewsFragmentKaverdram
import com.myandrappin.oiu.presentationKaverdram.saveNewsFragmentKaverdarm.SaveNewsFragmentKaverdarm
import com.myandrappin.oiu.presentationKaverdram.searchNewsFragmentKaverdarm.SearchNewsFragmentKaverdarm

class NewsFragmentKaverdarm : Fragment() {
    private val vmNewsFragmentCaverdarm by lazy { ViewModelProvider(this)[NewsFragmentViewModelKaverdarm::class.java] }
    private val headlinesAdapterCaverdarm by lazy { HeadlineAdapterKaverdarm(requireContext()) }
    private val lastNewsAdapterCaverdarm by lazy { LastNewsAdapterKaverdarm(requireContext()) }
    private lateinit var vbNewsFragmentCaverdarm: FragmentNewsCaverdarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbNewsFragmentCaverdarm = FragmentNewsCaverdarmBinding.inflate(inflater, container, false)
        return vbNewsFragmentCaverdarm.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmNewsFragmentCaverdarm.getFiveHeadlinesDataKaverdram()
        vmNewsFragmentCaverdarm.getLastTwentyNewsDataKaverdram()

        vmNewsFragmentCaverdarm.headlinesLiveDataKaverdarm.observe(viewLifecycleOwner) {
            headlinesAdapterCaverdarm.listOfHeadlinesKaverdarm = it.articlesKaverdarm
            vbNewsFragmentCaverdarm.viewPagerHeadlineNewsKaverdarm.adapter = headlinesAdapterCaverdarm
        }

        vmNewsFragmentCaverdarm.lastTwentyNewsLiveDataKaverdarm.observe(viewLifecycleOwner) {
            lastNewsAdapterCaverdarm.listOfLastNewsKaverdarm = it.articlesKaverdarm
            vbNewsFragmentCaverdarm.recyclerViewLastNewsKaverdarm.adapter = lastNewsAdapterCaverdarm
        }

        vbNewsFragmentCaverdarm.viewPagerHeadlineNewsKaverdarm.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                vbNewsFragmentCaverdarm.paginationHeadlineKaverdarm.setBackgroundResource(
                    when(position) {
                        0 -> R.drawable.pagination_1_dark_kaverdarm
                        1 -> R.drawable.pagination_2_dark_kaverdarm
                        2 -> R.drawable.pagination_3_dark_kaverdarm
                        3 -> R.drawable.pagination_4_dark_kaverdarm
                        4 -> R.drawable.pagination_5_dark_kaverdarm
                        else -> 0
                    }
                )
            }
        })

        headlinesAdapterCaverdarm.onHeadlineItemClickListenerKaverdarm = object : HeadlineAdapterKaverdarm.OnHeadlineItemClickListenerKaverdarm {
            override fun onHeadlineItemClickKaverdarm(
                titleKaverdram: String,
                imgKaverdram: String,
                dateKaverdram: String,
                descriptionKaverdram: String,
                contentKaverdram: String,
                bookmarkIsClickedKaverdram: Boolean
            ) {
                parentFragmentManager.beginTransaction()
                    .add(R.id.containerKaverdarm, newInstanceOpenedNewsFragmentKaverdram(titleKaverdram, imgKaverdram, dateKaverdram, descriptionKaverdram, contentKaverdram, bookmarkIsClickedKaverdram))
                    .commit()
            }
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
                    .add(R.id.containerKaverdarm, newInstanceOpenedNewsFragmentKaverdram(titleKaverdram, imgKaverdram, dateKaverdram, descriptionKaverdram, contentKaverdram, bookmarkIsClickedKaverdram))
                    .commit()
            }

            override fun onBookmarkBtnClickKaverdarm() {}
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity()
            }
        })

        vbNewsFragmentCaverdarm.btnSearchNewsKaverdarm.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.containerKaverdarm, SearchNewsFragmentKaverdarm())
                .commit()
        }

        vbNewsFragmentCaverdarm.btnOpenAllNewsKaverdarm.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.containerKaverdarm, AllNewsFragmentKaverdarm())
                .commit()
        }

        vbNewsFragmentCaverdarm.btnBookmarkNewsKaverdarm.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.containerKaverdarm, SaveNewsFragmentKaverdarm())
                .commit()
        }

        vbNewsFragmentCaverdarm.btnAddedNewsKaverdarm.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.containerKaverdarm, AddedNewsFragmentKaverdarm())
                .commit()
        }
    }
}