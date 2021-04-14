package com.example.mobillab.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.repo.database.CharacterDatabase
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        showCharacters()
    }

    override fun onDetach() {
        charactersPresenter.detachScreen()
        super.onDetach()
    }

    override fun showCharacters() {
        lifecycleScope.launch(Dispatchers.Main){
            var charString = ""

            val chars2 =  CharacterDatabase.getInstance(requireContext()).characterDao().getCharacters()
            chars2.forEach {  charString += chars2.toString() + "\n\n" }

            tv?.let {
                it.text = charString
            }
        delay(2000)
           val chars =  withContext(lifecycleScope.coroutineContext + Dispatchers.IO) { charactersPresenter.getCharacters() }

            charString = ""
            chars.forEach {  charString += chars.toString() + "\n\n" }



            tv?.let {
                it.text = charString
            }
        }
    }
}