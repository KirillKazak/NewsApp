package com.myandrappin.oiu.presentationKaverdram.searchNewsFragmentKaverdarm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.myandrappin.oiu.R
import com.myandrappin.oiu.databinding.FragmentSearchNewsKaverdarmBinding
import com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm.LastNewsAdapterKaverdarm
import com.myandrappin.oiu.presentationKaverdram.newsFragmentsKaverdarm.NewsFragmentViewModelKaverdarm
import com.myandrappin.oiu.presentationKaverdram.openedNewsKaverdram.OpenedNewsFragmentKaverdram

class SearchNewsFragmentKaverdarm : Fragment() {
    private lateinit var vbSearchNewsFragmentKaverdarm: FragmentSearchNewsKaverdarmBinding
    private val vmNewsFragmentCaverdarm by lazy { ViewModelProvider(this)[NewsFragmentViewModelKaverdarm::class.java] }
    private val lastNewsAdapterCaverdarm by lazy { LastNewsAdapterKaverdarm(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbSearchNewsFragmentKaverdarm = FragmentSearchNewsKaverdarmBinding.inflate(inflater, container, false)
        return vbSearchNewsFragmentKaverdarm.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmNewsFragmentCaverdarm.getLastTwentyNewsDataKaverdram()
        vmNewsFragmentCaverdarm.lastTwentyNewsLiveDataKaverdarm.observe(viewLifecycleOwner) {
            lastNewsAdapterCaverdarm.listOfLastNewsKaverdarm = it.articlesKaverdarm
            vbSearchNewsFragmentKaverdarm.recyclerViewSearchedNewsKaverdarm.adapter = lastNewsAdapterCaverdarm
        }
        vbSearchNewsFragmentKaverdarm.btnSearchKaverdarm.setOnClickListener {
            vmNewsFragmentCaverdarm.getNewsByKeyWordDataKaverdram(vbSearchNewsFragmentKaverdarm.edtSearchKaverdarm.text.toString())
            vbSearchNewsFragmentKaverdarm.tvSearchWordKaverdarm.text = getString(R.string.resultWordKaverdam) + vbSearchNewsFragmentKaverdarm.edtSearchKaverdarm.text.toString()
            vbSearchNewsFragmentKaverdarm.tvSearchWordKaverdarm.visibility = View.VISIBLE
            vbSearchNewsFragmentKaverdarm.btnCanselSearchWordKaverdarm.visibility = View.VISIBLE
            vbSearchNewsFragmentKaverdarm.btnCanselSearchWordKaverdarm.setOnClickListener {
                vbSearchNewsFragmentKaverdarm.tvSearchWordKaverdarm.visibility = View.INVISIBLE
                vbSearchNewsFragmentKaverdarm.btnCanselSearchWordKaverdarm.visibility = View.INVISIBLE
                vbSearchNewsFragmentKaverdarm.edtSearchKaverdarm.text.clear()
                vmNewsFragmentCaverdarm.getLastTwentyNewsDataKaverdram()
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
                    .detach(this@SearchNewsFragmentKaverdarm)
                    .commit()
            }
        })

        vbSearchNewsFragmentKaverdarm.btnGoBackSearchNewsKaverdram.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .detach(this@SearchNewsFragmentKaverdarm)
                .commit()
        }
    }
}