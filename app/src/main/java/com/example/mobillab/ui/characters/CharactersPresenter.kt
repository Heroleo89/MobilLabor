package com.example.mobillab.ui.characters

import android.content.Context
import android.widget.Toast
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.ui.Presenter
import kotlinx.coroutines.*
import java.lang.Exception
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

    fun getCharactersByName(name : String){
        MainScope().launch {
           val matchesCount =  withContext(Dispatchers.IO) {

               val matchesCount = try {
                   val matches = characterInteractor.getCharactersByName(name)
                   characterInteractor.database.insertCharacters(*matches.toTypedArray())
                   matches.size
               }catch (e : Exception){
                    0
               }

               matchesCount
            }

            if(matchesCount > 0 ){
                val characters = withContext(Dispatchers.IO) {
                    characterInteractor.database.getCharacters()
                }
                screen?.refreshList(characters)
                Toast.makeText(context,"$matchesCount character added.",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(context,"No character was found.",Toast.LENGTH_LONG).show()




        }
    }

    fun deleteCharacter(character : CharacterObj){
        MainScope().launch {
            val savedCharacters = withContext(Dispatchers.IO) {
                characterInteractor.database.deleteCharacter(character)
                getSavedCharacters() }
            screen?.refreshList(savedCharacters)
        }
    }

    private suspend fun saveCharacters(characters: List<CharacterObj>) {

        characterInteractor.saveCharacters(characters)
    }

    private suspend fun getSavedCharacters(): List<CharacterObj> {
        return characterInteractor.database.getCharacters()
    }
}