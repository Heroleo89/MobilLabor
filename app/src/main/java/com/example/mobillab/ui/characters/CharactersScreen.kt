package com.example.mobillab.ui.characters

import com.example.mobillab.model.CharacterObj

interface CharactersScreen {
    fun refreshList(characters : List<CharacterObj>)
    fun stopRefreshing()
}