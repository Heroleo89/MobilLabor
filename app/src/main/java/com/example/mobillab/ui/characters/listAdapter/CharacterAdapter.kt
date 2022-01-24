package com.example.mobillab.ui.characters.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.ui.characters.CharactersFragment

class CharacterAdapter(val fragment : CharactersFragment) : ListAdapter<CharacterObj, CharactersViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item, parent, false),fragment
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            holder.bindTo(getItem(position))
        }
    }
}