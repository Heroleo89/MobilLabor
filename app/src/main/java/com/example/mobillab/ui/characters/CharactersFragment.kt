package com.example.mobillab.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobillab.MainActivity
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import com.example.mobillab.model.CharacterObj
import com.example.mobillab.ui.characters.listAdapter.CharacterAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : Fragment(), CharactersScreen,OnButtonActionListener {

    companion object {
        const val NAME = "Characters"
    }

    @Inject
    lateinit var charactersPresenter: CharactersPresenter

    private lateinit var adapter: CharacterAdapter

    private lateinit var searchDialog : SearchDialog

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

        (context as MainActivity).onButtonClickListener = this
    }

    override fun onDetach() {
        charactersPresenter.detachScreen()
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()

        addButton?.setOnClickListener{

        }

        searchDialog =  SearchDialog(this,this::addSearch)

        setupListAdapter()

        swipe_container.setOnRefreshListener {
            charactersPresenter.updateCharacters()
        }

        charactersPresenter.loadCharacters()

    }

    override fun refreshList(characters: List<CharacterObj>) {
        swipe_container.isRefreshing = false
        adapter.submitList(characters)
    }

    override fun stopRefreshing() {
        swipe_container.isRefreshing = false
    }

    fun deleteCharacter(character: CharacterObj) {

        try {
            charactersPresenter.deleteCharacter(character)
        }catch (e: Exception){
            println("Couldn't delete Character : $character \n" + e.message )
        }
    }

    private fun setupListAdapter() {
        adapter = CharacterAdapter(this)
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = llm
        list.adapter = adapter
    }

    override fun onAction() {
        searchDialog.showDialog()
    }

    private fun addSearch(named : String){
        charactersPresenter.getCharactersByName(named)
    }
}

interface OnButtonActionListener{
    fun onAction()
}
