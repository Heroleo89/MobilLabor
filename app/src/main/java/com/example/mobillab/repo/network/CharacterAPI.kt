package com.example.mobillab.repo.network

import com.example.mobillab.model.CharacterObj
import retrofit2.http.*

interface CharacterAPI {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
    /**
     * Searches for characters with the given name
     *
     * @param name Character name
     * @return <List></List><Character>>
    </Character> */
    @GET("character")
    suspend fun getCharacters(
        @Query("name") name: String
    ): List<CharacterObj>

    /**
     * Update character
     *
     * @param body
     * @return Character?
    </Character> */
    @PUT("character")
    suspend fun updateCharacter(
        @Body body: CharacterObj
    ): CharacterObj?

    /**
     * Add character
     *
     * @param body
     * @return Character
    </Character> */
    @POST("character")
    suspend fun characterPost(
        @Body body: CharacterObj
    ): CharacterObj?

    /**
     * Gets characters with given ids
     *
     * @param id List of user IDs.
     * @return <List></List><Character>>
    </Character> */
    @GET("character/{id}")
    suspend fun getCharacters(
        @Path("id") id: List<Int>
    ): List<CharacterObj>

    /**
     * Delete character by ID
     *
     * @param id ID of the character that needs to be deleted
     * @return <Void>
    </Void> */
    @DELETE("character/{id}")
    suspend fun characterIdDelete(
        @Path("id") id: Int
    )
}
