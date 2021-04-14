package com.example.mobillab.repo.network

import com.example.mobillab.model.CharacterObj

class CharacterMockService: CharacterAPI {
    override suspend fun getCharacters(name: String): List<CharacterObj> {
        return emptyList()
    }

    override suspend fun getCharacters(id: List<Int>): List<CharacterObj> {
        return emptyList()
    }

    override suspend fun updateCharacter(body: CharacterObj): CharacterObj? {
        return null
    }

    override suspend fun characterPost(body: CharacterObj): CharacterObj? {
        return null
    }

    override suspend fun characterIdDelete(id: Int) {

    }
}