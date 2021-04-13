package com.example.mobillab.repo

import com.example.mobillab.repo.network.CharacterAPI
import javax.inject.Inject
import javax.inject.Named

class CharacterInteractor  @Inject constructor( @Named("provideCharacterApi")  val service : CharacterAPI){

}