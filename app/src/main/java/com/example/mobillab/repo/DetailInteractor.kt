package com.example.mobillab.repo

import com.example.mobillab.repo.database.CharacterDAO
import com.example.mobillab.repo.network.CharacterAPI
import javax.inject.Inject

class DetailInteractor @Inject constructor(val service : CharacterAPI,val database: CharacterDAO)
