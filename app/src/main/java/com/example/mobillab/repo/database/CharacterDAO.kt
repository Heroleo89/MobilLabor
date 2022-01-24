package com.example.mobillab.repo.database

import androidx.room.*
import com.example.mobillab.model.CharacterObj

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterObj")
    suspend fun getCharacters(): List<CharacterObj>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(vararg characters: CharacterObj)

    @Delete
    suspend fun deleteCharacter(character: CharacterObj)

    @Query("DELETE FROM CharacterObj")
    suspend fun deleteAllCharacters()

}