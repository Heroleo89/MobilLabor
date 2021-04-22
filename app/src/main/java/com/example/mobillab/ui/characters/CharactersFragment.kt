package com.example.mobillab.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobillab.MainApplication
import com.example.mobillab.R
import com.example.mobillab.repo.database.CharacterDatabase
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

        charactersPresenter.loadCharacters()

    }

    override fun onDetach() {
        charactersPresenter.detachScreen()
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()

        adapter = CharacterAdapter()
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = llm
        list.adapter = adapter

        showCharacters()

    }

    override fun showCharacters() {
        lifecycleScope.launch(Dispatchers.Main){
            var charString = ""

            val chars2 =  CharacterDatabase.getInstance(requireContext()).characterDao().getCharacters()

            adapter.submitList(chars2)


        delay(2000)
           val chars =  withContext(lifecycleScope.coroutineContext + Dispatchers.IO) { charactersPresenter.getCharacters() }
            adapter.submitList(chars)
    override fun refreshList(characters : List<CharacterObj>) {
        list?.let{
            it.text = characters.size.toString()
        }
    }
}