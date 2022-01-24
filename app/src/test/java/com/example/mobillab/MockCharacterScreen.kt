package com.example.mobillab

import com.example.mobillab.model.CharacterObj
import com.example.mobillab.ui.characters.CharactersScreen

class MockCharacterScreen : CharactersScreen {

    var refreshed : List<CharacterObj> = emptyList()

    override fun refreshList(characters: List<CharacterObj>) {
            refreshed = characters
    }

    override fun stopRefreshing() {

    }
}