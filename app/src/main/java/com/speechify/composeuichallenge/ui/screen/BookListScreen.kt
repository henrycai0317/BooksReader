package com.speechify.composeuichallenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.speechify.composeuichallenge.data.Book
import com.speechify.composeuichallenge.ui.component.BooksItemView
import com.speechify.composeuichallenge.ui.component.LoadingView
import com.speechify.composeuichallenge.ui.component.SearchBarView
import com.speechify.composeuichallenge.viewmodel.BooksUiState
import com.speechify.composeuichallenge.viewmodel.BooksViewmodel

@Composable
fun BookListScreen(
    onBookClick: (String) -> Unit,
    viewmodel: BooksViewmodel = hiltViewModel()
) {

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    LoadingView()
                }

                uiState.books.isNotEmpty() -> {
                    BookListContent(paddingValues, uiState, viewmodel, onBookClick)
                }
            }
        }
    }


}

@Composable
private fun BookListContent(
    paddingValues: PaddingValues,
    uiState: BooksUiState,
    viewmodel: BooksViewmodel,
    onBookClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .padding(24.dp)
    ) {
        SearchBarView(
            query = uiState.searchQuery,
            onQueryChange = { viewmodel.searchBooks(it) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = uiState.books,
                key = { it.imageUrl }
            ) { book ->
                BooksItemView(
                    book = book,
                    onClick = { onBookClick(book.id) }
                )
            }
        }
    }
}

// Preview 用的測試資料
private val previewBooks = listOf(
    Book(
        id = "1",
        name = "The Great Gatsby",
        author = "F. Scott Fitzgerald",
        imageUrl = "https://example.com/gatsby.jpg",
        description = "A classic novel set in the Jazz Age",
        rating = 4.5f,
        reviewCount = 2543
    ),
    Book(
        id = "2",
        name = "To Kill a Mockingbird",
        author = "Harper Lee",
        imageUrl = "https://example.com/mockingbird.jpg",
        description = "A story of racial injustice and childhood innocence",
        rating = 4.8f,
        reviewCount = 3891
    ),
    Book(
        id = "3",
        name = "1984",
        author = "George Orwell",
        imageUrl = "https://example.com/1984.jpg",
        description = "A dystopian social science fiction novel",
        rating = 4.6f,
        reviewCount = 4201
    )
)

@Composable
private fun BookListScreenContent(
    books: List<Book>,
    searchQuery: String,
    onBookClick: (String) -> Unit,
    onQueryChange: (String) -> Unit
) {
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            SearchBarView(
                modifier = Modifier.padding(horizontal = 16.dp),
                query = searchQuery,
                onQueryChange = onQueryChange
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = books,
                    key = { it.imageUrl }
                ) { book ->
                    BooksItemView(
                        book = book,
                        onClick = { onBookClick(book.id) }
                    )
                }
            }
        }
    }
}

@Preview(
    name = "書籍列表 - 有資料",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun BookListScreenPreview() {
    BookListScreenContent(
        books = previewBooks,
        searchQuery = "",
        onBookClick = {},
        onQueryChange = {}
    )
}

@Preview(
    name = "書籍列表 - 搜尋中",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun BookListScreenSearchingPreview() {
    BookListScreenContent(
        books = previewBooks,
        searchQuery = "Great",
        onBookClick = {},
        onQueryChange = {}
    )
}

@Preview(
    name = "書籍列表 - 空列表",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun BookListScreenEmptyPreview() {
    BookListScreenContent(
        books = emptyList(),
        searchQuery = "",
        onBookClick = {},
        onQueryChange = {}
    )
}
