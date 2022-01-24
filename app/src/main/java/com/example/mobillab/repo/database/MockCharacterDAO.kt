package com.example.mobillab.repo.database

import com.example.mobillab.model.CharacterObj

class MockCharacterDAO : CharacterDAO {

    companion object{
        var MOCK_DATA : MutableList<CharacterObj> = mutableListOf()
        init{
            val url = "https://cdn.w600.comps.canstockphoto.com/can-stock-photo_csp48405438.jpg"
            for (i in 0 until 20){
                MOCK_DATA.add(CharacterObj(i, "name$i","alive",image = url))
            }
        }
    }

    override suspend fun getCharacters(): List<CharacterObj> {
        return mutableListOf(*MOCK_DATA.toTypedArray())
    }

    override suspend fun insertCharacters(vararg characters: CharacterObj) {
        for( char in characters){
            MOCK_DATA.add(char)
        }
    }

    override suspend fun deleteCharacter(character: CharacterObj) {
        MOCK_DATA.remove(character)
    }

    override suspend fun deleteAllCharacters() {
        MOCK_DATA.clear()
    }
}