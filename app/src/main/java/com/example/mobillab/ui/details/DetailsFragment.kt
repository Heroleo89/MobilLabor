package com.example.mobillab.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobillab.MainApplication
import com.example.mobillab.R
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

    override fun onDetach() {
        detailsPresenter.detachScreen()
        super.onDetach()
    }
}