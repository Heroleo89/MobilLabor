package com.example.mobillab.ui.about

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobillab.MainActivity
import com.example.mobillab.R
import kotlinx.android.synthetic.main.activity_main.*

class AboutFragment : Fragment() {

    companion object{
        const val NAME = "About"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}