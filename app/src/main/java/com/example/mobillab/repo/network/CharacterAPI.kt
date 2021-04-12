package com.example.mobillab.repo.network

import com.example.mobillab.model.Character
import retrofit2.http.GET

interface CharacterAPI {
    @GET("character/")
    suspend fun getCharacters() : List<Character>
}