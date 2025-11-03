package com.speechify.composeuichallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.speechify.composeuichallenge.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewmodel @Inject constructor(
    val repository: BooksRepository
): ViewModel() {


}