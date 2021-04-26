package com.example.mobillab

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.mobillab.ui.about.AboutFragment
import com.example.mobillab.ui.characters.CharactersFragment
import com.example.mobillab.ui.characters.OnButtonActionListener
import com.example.mobillab.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var navIcon: Drawable? = null

    var onButtonClickListener : OnButtonActionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        addButton.setOnClickListener {
            onButtonClickListener?.onAction()
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
                addButton.visibility = View.INVISIBLE
            }
            CharactersFragment::class -> {
                toolBar.setToolBar(CharactersFragment.NAME, null, true)
                addButton.visibility = View.VISIBLE
            }
            DetailsFragment::class -> {
                toolBar.setToolBar(DetailsFragment.NAME, navIcon, false)
                addButton.visibility = View.INVISIBLE
            }
        }
    }
}

fun Toolbar.setToolBar(name: String, icon: Drawable?, isMenuVisible: Boolean) {
    menu.setGroupVisible(0, isMenuVisible)
    navigationIcon = icon
    title = name
}
