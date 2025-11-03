package com.speechify.composeuichallenge.navigation

sealed class NavigationRouter(val route: String) {

    object BookList : NavigationRouter("book_list")

    data class BookDetail(val bookJson: String) : NavigationRouter("book_detail/${bookJson}") {
        companion object {
            const val ROUTE_PATTERN = "book_detail/{bookJson}"
            const val ARG_BOOK_JSON = "bookJson"

//            private val gson = Gson()

        }
    }

}