package com.example.mobillab.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import com.example.mobillab.repo.DetailInteractor
import com.example.mobillab.ui.characters.CharactersPresenter
import javax.inject.Inject

class DetailsFragment  : Fragment(),DetailsScreen {

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
        super.onDetach()
        detailsPresenter.detachScreen()
    }
}