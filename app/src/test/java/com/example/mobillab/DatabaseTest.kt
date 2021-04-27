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
class DatabaseTest {

    @Inject
    lateinit var charactersPresenter: CharactersPresenter

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
    fun dataBaseLoadTest() {

        runBlocking {
            launch(Dispatchers.Main) {
                val chars = characterInteractor.database.getCharacters()
                assertThat("Couldn't get data from database", chars.isNotEmpty())
            }
        }
    }


    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun dataBaseInsertTest() {

        runBlocking {
            launch(Dispatchers.Main) {

                val chars = characterInteractor.database.getCharacters()
                val sizeBeforeInsert = chars.size
                characterInteractor.database.insertCharacters(CharacterObj(102012))
                val chars2 = characterInteractor.database.getCharacters()
                val sizeAfterInsert = chars2.size

                assertThat(
                    "Couldn't insert data to database",
                    sizeBeforeInsert + 1 == sizeAfterInsert
                )
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun dataBaseDeleteTest() {

        runBlocking {
            launch(Dispatchers.Main) {

                characterInteractor.database.insertCharacters(CharacterObj(123312))
                val chars = characterInteractor.database.getCharacters()
                val sizeBeforeDelete = chars.size
                val toDelete = chars[0]
                characterInteractor.database.deleteCharacter(toDelete)

                val chars2 = characterInteractor.database.getCharacters()
                val sizeAfterDelete = chars2.size

                assertThat(
                    "Couldn't delete character from database",
                    sizeBeforeDelete - 1 == sizeAfterDelete && !chars2.contains(toDelete)
                )
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun dataBaseDeleteAllTest() {

        runBlocking {
            launch(Dispatchers.Main) {
                characterInteractor.database.deleteAllCharacters()
                val chars = characterInteractor.database.getCharacters()

                assertThat("Couldn't delete character from database", chars.isEmpty())
            }
        }
    }
}