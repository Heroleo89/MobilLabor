package com.example.mobillab.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import com.example.mobillab.ui.characters.listAdapter.CharacterAdapter
import com.example.mobillab.model.CharacterObj
import kotlinx.android.synthetic.main.fragment_characters.*

import javax.inject.Inject

class CharactersFragment : Fragment(), CharactersScreen {

    companion object{
        const val NAME = "Characters"
    }

    @Inject
    lateinit var charactersPresenter: CharactersPresenter

    lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).injector.inject(this)
        charactersPresenter.attachScreen(this)
    }

    override fun onDetach() {
        charactersPresenter.detachScreen()
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()

        setupListAdapter()

        charactersPresenter.loadCharacters()

    }

    override fun refreshList(characters : List<CharacterObj>) {
        adapter.submitList(characters)
    }

    private fun setupListAdapter(){
        adapter = CharacterAdapter()
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = llm
        list.adapter = adapter
    }
}
