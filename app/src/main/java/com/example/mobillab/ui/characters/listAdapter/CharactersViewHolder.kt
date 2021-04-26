package com.example.mobillab.ui.characters.listAdapter

import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobillab.MainActivity
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.ui.characters.CharactersFragment
import kotlinx.android.synthetic.main.character_item.view.*
import java.util.*
import javax.inject.Inject

class CharactersViewHolder (itemView: View,val fragment: CharactersFragment) : RecyclerView.ViewHolder(itemView) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun bindTo(char: CharacterObj){
        itemView.findViewById<TextView>(R.id.name).text = char.name
        itemView.findViewById<TextView>(R.id.species).text = char.species

        val colorId = if (char.status?.toLowerCase(Locale.ROOT) == "dead") R.color.red else R.color.green

        itemView.findViewById<ImageView>(R.id.lifeBar).setBackgroundColor(itemView.context.resources.getColor(colorId,null))

        Glide
            .with(fragment)
            .load(char.image)
            .circleCrop()
            .into(itemView.findViewById(R.id.image))

        itemView.findViewById<ImageView>(R.id.delete).setOnClickListener {
            fragment.deleteCharacter(char)
        }

        itemView.setOnClickListener {
            val bundle = bundleOf("character" to char)
            (fragment.requireActivity() as MainActivity ).navController.navigate(R.id.charactersToDetail,bundle)
        }

    }

}