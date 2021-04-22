package com.example.mobillab.ui.characters.listAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj
import kotlinx.android.synthetic.main.character_item.view.*

class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(char: CharacterObj){
        itemView.findViewById<TextView>(R.id.name).text = char.name
    }

}