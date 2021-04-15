package com.example.mobillab.repo.database

import com.example.mobillab.model.CharacterObj

class MockCharacterDAO : CharacterDAO {
    override suspend fun getCharacters(): List<CharacterObj> {
        return emptyList()
    }

    override suspend fun insertCharacters(vararg characters: CharacterObj) {
    }

    override suspend fun deleteCharacter(character: CharacterObj) {
    }

    override suspend fun deleteAllCharacters() {
    }
}