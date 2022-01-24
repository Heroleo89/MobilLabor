package com.example.mobillab

import android.os.Build
import com.example.mobillab.di.testInjector
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.repo.CharacterInteractor
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
class InteractorTest {
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
    fun getRandomCharactersTest() {

        runBlocking {
            launch(Dispatchers.Main) {
               val chars = characterInteractor.getRandomCharacters()

                assertThat("Couldn't get random characters", chars.size == 20)
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun saveCharactersTest() {

        runBlocking {
            launch(Dispatchers.Main) {

                characterInteractor.saveCharacters(listOf(CharacterObj(1111)))
                val saved = characterInteractor.database.getCharacters().find { it.id == 1111 }

                assertThat("Couldn't save characters", saved != null)
            }
        }
    }
}