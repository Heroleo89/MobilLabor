package com.example.mobillab

import android.os.Build
import com.example.mobillab.di.testInjector
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

@RunWith(RobolectricTestRunner::class)
class PresenterTest {
    @Inject
    lateinit var charactersPresenter: CharactersPresenter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val mockScreen = MockCharacterScreen()

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        Dispatchers.setMain(mainThreadSurrogate)
        charactersPresenter.attachScreen(mockScreen)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        charactersPresenter.detachScreen()
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun presenterLoadTest() {

        runBlocking {
            withContext(Dispatchers.IO) {
                charactersPresenter.loadCharacters()
            }
            delay(500)
            assertThat("Couldn't load characters", mockScreen.refreshed.isNotEmpty())
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun presenterUpdateTest() {

        runBlocking {
            val listBefore = mockScreen.refreshed
            withContext(Dispatchers.IO) {
                charactersPresenter.updateCharacters()
            }

            delay(1500)
            assertThat(
                "Couldn't refresh character list",
                mockScreen.refreshed.hashCode() != listBefore.hashCode()
            )
        }
    }
}