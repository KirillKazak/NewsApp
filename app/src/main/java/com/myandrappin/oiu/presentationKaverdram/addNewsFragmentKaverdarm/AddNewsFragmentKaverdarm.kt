package com.myandrappin.oiu.presentationKaverdram.addNewsFragmentKaverdarm

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.myandrappin.oiu.R
import com.myandrappin.oiu.dataKaverdram.roomKaverdarm.DatabaseAddNewsKaverdarm
import com.myandrappin.oiu.databinding.FragmentAddNewsKaverdarmBinding
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntityAddNewsKaverdarm
import com.myandrappin.oiu.utilKaverdram.PhotoReceiverKaverdarm
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram

class AddNewsFragmentKaverdarm : Fragment(), PhotoReceiverKaverdarm {
    private lateinit var vbAddNewsFragmentKaverdarm: FragmentAddNewsKaverdarmBinding
    private var tUriKaverdarm = Uri.EMPTY
    private val databaseAddedNewsKaverdarm by lazy {
        Room.databaseBuilder(
            requireContext(), DatabaseAddNewsKaverdarm::class.java,
            UtilKaverdram.roomDatabaseNameAddedKaverdram
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val activityResultLauncherGalleryKaverdram =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            tUriKaverdarm =
                if (it.data == null || it.resultCode != RESULT_OK) {
                    null
                } else it.data!!.data
            if (tUriKaverdarm != null) {
                Glide.with(this)
                    .load(arrayOf(tUriKaverdarm)[0])
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(vbAddNewsFragmentKaverdarm.btnAddImgForNewsKaverdarm)
            } else {
                tUriKaverdarm = PhotoReceiverKaverdarm.uriKaverdarm
                Glide.with(this)
                    .load(tUriKaverdarm)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(vbAddNewsFragmentKaverdarm.btnAddImgForNewsKaverdarm)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbAddNewsFragmentKaverdarm = FragmentAddNewsKaverdarmBinding.inflate(inflater, container, false)
        return vbAddNewsFragmentKaverdarm.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(vbAddNewsFragmentKaverdarm) {

            btnAddImgForNewsKaverdarm.setOnClickListener {
                val intentKaverdarm = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (intentKaverdarm.resolveActivity(requireActivity().packageManager) != null) {
                    onReciverExistKaverdarm(intentKaverdarm, requireActivity() as AppCompatActivity, activityResultLauncherGalleryKaverdram)
                }
            }

            vbAddNewsFragmentKaverdarm.btnCreateNewsKaverdarm.setOnClickListener {
                if (tUriKaverdarm == Uri.EMPTY || edtAddTitleKaverdarm.text.isEmpty() || edtAddDescriptionKaverdarm.text.isEmpty() || edtAddContentKaverdarm.text.isEmpty()) {
                    Toast.makeText(requireContext(), getString(R.string.dontEnterContentKaverdarm), Toast.LENGTH_SHORT).show()
                } else {
                    databaseAddedNewsKaverdarm.listAddedNewsDaoKaverdarm().addItemToAddDatabaseKaverdarm(
                        DatabaseEntityAddNewsKaverdarm(
                            edtAddTitleKaverdarm.text.toString(),
                            tUriKaverdarm.toString(),
                            edtAddDescriptionKaverdarm.text.toString(),
                            edtAddContentKaverdarm.text.toString()
                        )
                    )
                    parentFragmentManager.beginTransaction().detach(this@AddNewsFragmentKaverdarm).detach(AddedNewsFragmentKaverdarm()).add(R.id.containerKaverdarm, AddedNewsFragmentKaverdarm()).commit()
                }
            }

            btnGoBackAddNewsKaverdram.setOnClickListener {
                parentFragmentManager.beginTransaction().detach(this@AddNewsFragmentKaverdarm).commit()
            }

            requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.beginTransaction().detach( this@AddNewsFragmentKaverdarm).commit()
                }
            })
        }
    }
}