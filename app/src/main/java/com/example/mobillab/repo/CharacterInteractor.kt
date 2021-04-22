package com.example.mobillab.repo

import com.example.mobillab.model.CharacterObj
import com.example.mobillab.repo.database.CharacterDAO
import com.example.mobillab.repo.network.CharacterAPI
import javax.inject.Inject
import kotlin.random.Random

class CharacterInteractor  @Inject constructor( val service : CharacterAPI,val database: CharacterDAO){

    suspend fun getRandomCharacters(): List<CharacterObj>{

        return service.getCharacters( getUniqueRandomIndices(20))
    }

    suspend fun saveCharacters(characters : List<CharacterObj>){
        database.insertCharacters(*characters.toTypedArray())
    }

    private fun getUniqueRandomIndices(n : Int) : List<Int>{
        val list = mutableListOf<Int>()
        val possibleChoices  =  MutableList(671){it}

        repeat(n){
            val i = Random.nextInt(0,possibleChoices.size-1)
            val randomIndex = possibleChoices[i]
            possibleChoices.removeAt(i)
            list.add(randomIndex)
        }

        return list
    }
}