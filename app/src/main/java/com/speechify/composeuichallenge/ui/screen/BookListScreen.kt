package com.speechify.composeuichallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.speechify.composeuichallenge.data.Book
import com.speechify.composeuichallenge.ui.component.SearchBarView
import com.speechify.composeuichallenge.viewmodel.BooksViewmodel

@Composable
fun BookListScreen(
    onBookClick: (Book) -> Unit,
    viewmodel: BooksViewmodel = hiltViewModel()
) {

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {

        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            SearchBarView(
                query =uiState.searchQuery,
                onQueryChange = { viewmodel.searchBooks() }
            )

        }
    }


}