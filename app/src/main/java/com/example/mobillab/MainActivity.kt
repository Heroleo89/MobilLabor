package com.example.mobillab

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Debug
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.mobillab.model.Character
import com.example.mobillab.repo.network.CharacterAPI
import com.example.mobillab.repo.network.CharacterInterceptor
import com.example.mobillab.ui.about.AboutFragment
import com.example.mobillab.ui.characters.CharactersFragment
import com.example.mobillab.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    private lateinit var retrofit: Retrofit
    private lateinit var navController: NavController
    private var navIcon: Drawable? = null

    lateinit var characterService : CharacterAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRetrofit()

        navIcon = ContextCompat.getDrawable(this, R.drawable.baseline_arrow_back_white_18dp)

        setNavigationListeners()
    }

    override fun onResume() {

        navController = nav_host_fragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.aboutFragment -> setToolbarForFragment(AboutFragment::class)
                R.id.charactersFragment -> setToolbarForFragment(CharactersFragment::class)
                R.id.detailsFragment -> setToolbarForFragment(DetailsFragment::class)
            }
        }

        var chars: List<Character>
        lifecycleScope.launch(Dispatchers.Main) {

            withContext(lifecycleScope.coroutineContext + Dispatchers.IO) {
               chars = characterService.getCharacters()
            }

            println(chars.size)
        }


        super.onResume()
    }

    private fun setNavigationListeners() {
        toolBar.setNavigationOnClickListener {
            if (navController.currentDestination?.id == R.id.aboutFragment || navController.currentDestination?.id == R.id.detailsFragment) {

                if (navController.currentDestination?.id == R.id.aboutFragment) {
                    navController.navigate(R.id.aboutToCharacters)
                } else if (navController.currentDestination?.id == R.id.detailsFragment) {
                    navController.navigate(R.id.detailToCharacters)
                }
            }
        }

        toolBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.about && navController.currentDestination?.id == R.id.charactersFragment) {
                navController.navigate(R.id.charactersToAbout)
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun <T : Fragment> setToolbarForFragment(fragment: KClass<T>) {
        when (fragment) {
            AboutFragment::class -> {
                toolBar.setToolBar(AboutFragment.NAME, navIcon, false)
            }
            CharactersFragment::class -> {
                toolBar.setToolBar(CharactersFragment.NAME, null, true)
            }
            DetailsFragment::class -> {
                toolBar.setToolBar(DetailsFragment.NAME, navIcon, false)
            }
        }
    }

    private fun setupRetrofit() {

        val okHttpClient = OkHttpClient()
                            .newBuilder()
                            .addInterceptor(CharacterInterceptor())
                            .build()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        characterService = retrofit.create(CharacterAPI::class.java)
    }
}

fun Toolbar.setToolBar(name: String, icon: Drawable?, isMenuVisible: Boolean) {
    menu.setGroupVisible(0, isMenuVisible)
    navigationIcon = icon
    title = name
}
