package com.speechify.composeuichallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speechify.composeuichallenge.data.Book
import com.speechify.composeuichallenge.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BooksUiState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

@HiltViewModel
class BooksViewmodel @Inject constructor(
    val repository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()


    init {
        loadAllBooks()
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(searchQuery = query) }
            val result = repository.searchBook(query = query)
            _uiState.update { it.copy(isLoading = false, books = result) }
        }
    }

    private fun loadAllBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.getBooks()
            _uiState.update { it.copy(isLoading = false, books = result) }
        }

    }


}