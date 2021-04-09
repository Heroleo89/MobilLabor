package com.example.mobillab.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import javax.inject.Inject

class CharactersFragment  : Fragment(),CharactersScreen {

    companion object{
        const val NAME = "Characters"
    }

    @Inject
    lateinit var  charactersPresenter: CharactersPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).injector.inject(this)
        charactersPresenter.attachScreen(this)
    }

    override fun onDetach() {
        charactersPresenter.detachScreen()
        super.onDetach()
    }
}