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

    fun loadCharacters() {

        MainScope().launch {

            val savedCharacters = withContext(Dispatchers.IO) { getSavedCharacters() }
            screen?.refreshList(savedCharacters)

            if (savedCharacters.isEmpty()) {
                val refreshed =
                    withContext(Dispatchers.IO) { characterInteractor.getRandomCharacters() }
                screen?.refreshList(refreshed)
                saveCharacters(refreshed)
            }
        }
    }

    private suspend fun saveCharacters(characters: List<CharacterObj>) {

        characterInteractor.saveCharacters(characters)
    }

    private suspend fun getSavedCharacters(): List<CharacterObj> {
        return characterInteractor.database.getCharacters()
    }
}