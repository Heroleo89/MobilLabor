package com.example.mobillab.ui.characters

import android.content.Context
import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.ui.Presenter
import javax.inject.Inject

class CharactersPresenter @Inject constructor(private val characterInteractor: CharacterInteractor, private val context: Context)  : Presenter<CharactersScreen?>()