package com.example.mobillab

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.mobillab.ui.about.AboutFragment
import com.example.mobillab.ui.characters.CharactersFragment
import com.example.mobillab.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private var navIcon: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navIcon = ContextCompat.getDrawable(this,R.drawable.baseline_arrow_back_white_18dp)

        setNavigationListeners()
    }

    override fun onResume() {

        navController = nav_host_fragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.aboutFragment ->   setToolbarForFragment(AboutFragment::class)
                R.id.charactersFragment ->   setToolbarForFragment(CharactersFragment::class)
                R.id.detailsFragment ->   setToolbarForFragment(DetailsFragment::class)
            }
        }

        super.onResume()
    }

    private fun setNavigationListeners(){
        toolBar.setNavigationOnClickListener {
            if(navController.currentDestination?.id == R.id.aboutFragment || navController.currentDestination?.id == R.id.detailsFragment  ){

                if(navController.currentDestination?.id == R.id.aboutFragment){
                    navController.navigate(R.id.aboutToCharacters)
                }
                else if (navController.currentDestination?.id == R.id.detailsFragment){
                    navController.navigate(R.id.detailToCharacters)
                }
            }
        }

        toolBar.setOnMenuItemClickListener {
            if(it.itemId == R.id.about && navController.currentDestination?.id == R.id.charactersFragment ) {
                navController.navigate(R.id.charactersToAbout)
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun <T : Fragment> setToolbarForFragment(fragment: KClass<T>){
        when(fragment){
            AboutFragment::class -> {
                toolBar.menu.setGroupVisible(0,false)
                toolBar.navigationIcon = navIcon
                toolBar.title = AboutFragment.NAME
            }
            CharactersFragment::class -> {
                toolBar.menu.setGroupVisible(0,true)
                toolBar.navigationIcon = null
                toolBar.title = CharactersFragment.NAME
            }
            DetailsFragment::class -> {
                toolBar.menu.setGroupVisible(0,false)
                toolBar.navigationIcon = navIcon
                toolBar.title = DetailsFragment.NAME
            }
        }
    }

}