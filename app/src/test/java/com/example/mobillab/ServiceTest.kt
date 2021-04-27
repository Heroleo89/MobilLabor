package com.example.mobillab

import android.os.Build
import com.example.mobillab.di.testInjector
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.ui.characters.CharactersPresenter
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ServiceTest {

    @Inject
    lateinit var characterInteractor: CharacterInteractor

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun serviceGetCharacterByNameTest() {
        runBlocking {
            launch(Dispatchers.Main) {

                val chars = characterInteractor.service.getCharacters("100")

                val invalidNames = characterInteractor.service.getCharacters("q34t4g5fvb")

                assertThat("Couldn't get character by name from server", chars.isNotEmpty() && invalidNames.isEmpty())
            }
        }
    }


    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun serviceGetCharacterByIdTest() {
        runBlocking {
            launch(Dispatchers.Main) {

                val chars = characterInteractor.service.getCharacters(listOf(1,2,3,4,5))

                assertThat("Couldn't get character by id from server", chars.size == 5)
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun servicePostCharacterTest() {
        runBlocking {
            launch(Dispatchers.Main) {

                val posted = characterInteractor.service.characterPost(CharacterObj(1000))
                val chars =  characterInteractor.service.getCharacters(listOf(1000))

                assertThat("Couldn't post character to server", chars.size == 1 && posted != null)
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun servicePutCharacterTest() {
        runBlocking {
            launch(Dispatchers.Main) {

                val char =  characterInteractor.service.getCharacters(listOf(0))[0]

                val updated = characterInteractor.service.updateCharacter(CharacterObj(0,"newName"))

                val charUpdated =  characterInteractor.service.getCharacters(listOf(0))[0]

                assertThat("Couldn't update character on server", updated != null && char.name != charUpdated.name && charUpdated.name == "newName")
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun serviceDeleteCharacterTest() {
        runBlocking {
            launch(Dispatchers.Main) {

                val toDelete =  characterInteractor.service.getCharacters(listOf(600))
                                characterInteractor.service.characterIdDelete(600)
                val deleted =  characterInteractor.service.getCharacters(listOf(600))

                assertThat("Couldn't update character on server", toDelete.isNotEmpty() && deleted.isEmpty() )
            }
        }
    }
}