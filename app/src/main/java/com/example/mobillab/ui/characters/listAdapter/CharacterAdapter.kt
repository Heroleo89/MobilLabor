package com.example.mobillab.ui.characters.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj

class CharacterAdapter : ListAdapter<CharacterObj, CharactersViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

}