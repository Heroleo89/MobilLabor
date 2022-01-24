package com.example.mobillab.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment  : Fragment(),DetailsScreen {

    companion object{
        const val NAME = "Character - Detail"
    }

    @Inject
    lateinit var  detailsPresenter: DetailsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).injector.inject(this)
        detailsPresenter.attachScreen(this)
    }

    override fun onResume() {
        super.onResume()
        val char = arguments?.get("character") as? CharacterObj

        setupDetails(char)
    }
    private fun setupDetails(char : CharacterObj?){

        char?.let {
            Glide
                .with(this)
                .load(char.image)
                .centerCrop()
                .into(detail_image)

            tv_name_text.text = char.name
            tv_status_text.text = char.status
            tv_species_text.text = char.species
            tv_gender_text.text = char.type ?: "-"
            tv_origin_text.text = char.origin?.name
            tv_location_text.text = char.location?.name

        }


    }

    override fun onDetach() {
        detailsPresenter.detachScreen()
        super.onDetach()
    }
}