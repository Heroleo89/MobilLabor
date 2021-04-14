package com.example.mobillab.repo.network

import com.example.mobillab.model.Character

class CharacterMockService: CharacterAPI {
    override suspend fun getCharacters(name: String): List<Character> {
        return emptyList()
    }

    override suspend fun getCharacters(id: List<Int>): List<Character> {
        return emptyList()
    }

    override suspend fun updateCharacter(body: Character): Character? {
        return null
    }

    override suspend fun characterPost(body: Character): Character? {
        return null
    }

    override suspend fun characterIdDelete(id: Int) {

    }
}