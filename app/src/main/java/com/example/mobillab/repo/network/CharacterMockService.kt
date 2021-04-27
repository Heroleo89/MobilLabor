package com.example.mobillab.repo.network

import com.example.mobillab.model.CharacterObj
import kotlinx.coroutines.delay

class CharacterMockService : CharacterAPI {

    companion object {
        var MOCK_DATA: MutableList<CharacterObj> = mutableListOf()
    }

    init {
        val url = "https://cdn.w600.comps.canstockphoto.com/can-stock-photo_csp48405438.jpg"
        for (i in 0 until 671) {
            MOCK_DATA.add(CharacterObj(i, "nameFromNet$i", "alive", image = url))
        }
    }

    override suspend fun getCharacters(name: String): List<CharacterObj> {
        delay(1000)
        return MOCK_DATA.filter { it.name?.contains(name) ?: false }
    }

    override suspend fun getCharacters(id: List<Int>): List<CharacterObj> {
        var list = mutableListOf<CharacterObj>()
        for (i in id) {
            val match = MOCK_DATA.find { it.id == i }
            if (match != null)
                list.add(match)
        }
        delay(1000)
        return list
    }

    override suspend fun updateCharacter(body: CharacterObj): CharacterObj? {
        delay(500)
        val match =  MOCK_DATA.find { it.id == body.id }

        if(match != null){
            MOCK_DATA.remove(match)
            MOCK_DATA.add(body)
            return body
        }

        return null
    }

    override suspend fun characterPost(body: CharacterObj): CharacterObj? {
        delay(500)
        val match =  MOCK_DATA.find { it.id == body.id }

        if(match == null){
            MOCK_DATA.add(body)
            return body
        }
        return null
    }

    override suspend fun characterIdDelete(id: Int) {
        delay(500)
        val match =  MOCK_DATA.find { it.id == id }
        match?.let {
            MOCK_DATA.remove(match)
        }
    }
}