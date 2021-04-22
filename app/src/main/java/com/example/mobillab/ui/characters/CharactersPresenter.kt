package com.example.mobillab.ui.characters

import android.content.Context
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.ui.Presenter
import kotlinx.coroutines.*
import javax.inject.Inject

class CharactersPresenter @Inject constructor(
    private val characterInteractor: CharacterInteractor,
    private val context: Context
) : Presenter<CharactersScreen?>() {

    fun loadCharacters(){

        MainScope().launch {
            val savedCharacters =  withContext( Dispatchers.IO) { getSavedCharacters() }
            screen?.refreshList(savedCharacters)

            val refreshed = withContext( Dispatchers.IO) { characterInteractor.getRandomCharacters() }
            delay(2000)
            screen?.refreshList(refreshed)
        }
    }

    private suspend fun getSavedCharacters(): List<CharacterObj> {
        return characterInteractor.database.getCharacters()
    }
}