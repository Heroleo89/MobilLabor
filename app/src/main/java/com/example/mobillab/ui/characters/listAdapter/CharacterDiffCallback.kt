package com.example.mobillab.ui.characters.listAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mobillab.model.CharacterObj

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterObj>() {

    override fun areItemsTheSame(oldItem: CharacterObj, newItem: CharacterObj): Boolean  = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CharacterObj, newItem: CharacterObj): Boolean = oldItem.id == newItem.id
}