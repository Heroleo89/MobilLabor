package com.example.mobillab.ui.details

import android.content.Context
import android.widget.Toast
import com.example.mobillab.repo.DetailInteractor
import com.example.mobillab.ui.Presenter
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val detailInteractor: DetailInteractor,private val context: Context) : Presenter<DetailsScreen?>()