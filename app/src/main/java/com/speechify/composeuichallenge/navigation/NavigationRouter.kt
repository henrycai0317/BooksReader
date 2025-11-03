package com.speechify.composeuichallenge.navigation

sealed class NavigationRouter(val route: String) {

    object BookList : NavigationRouter("book_list")

    object BookDetail: NavigationRouter("book_detail/{bookId}") {
        fun passBookId(bookId: String) = "book_detail/$bookId"
    }

}