package com.example.mobillab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.mobillab.ui.characters.CharactersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        startFragment()
    }

    private fun startFragment(){
        supportFragmentManager.commit {
            add<CharactersFragment>(R.id.container)
        }
    }
}