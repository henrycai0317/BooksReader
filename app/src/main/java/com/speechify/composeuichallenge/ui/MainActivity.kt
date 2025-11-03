package com.speechify.composeuichallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.speechify.composeuichallenge.navigation.NavigationRouter
import com.speechify.composeuichallenge.ui.screen.BookDetailScreen
import com.speechify.composeuichallenge.ui.screen.BookListScreen
import com.speechify.composeuichallenge.ui.theme.ComposeUIChallengeTheme
import com.speechify.composeuichallenge.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUIChallengeTheme {
                BookListNavigator()
            }
        }
    }
}

@Composable
fun BookListNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = NavigationRouter.BookList.route
    ) {
        composable(route = NavigationRouter.BookList.route) {
            BookListScreen(
                onBookClick = { bookId ->
                    navController.navigate(
                        NavigationRouter.BookDetail.passBookId(
                            bookId = bookId
                        )
                    )
                })
        }

        composable(
            route = NavigationRouter.BookDetail.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString(DETAILS_ARGUMENT_KEY)
            BookDetailScreen(bookId = bookId ?: "", onBackClick = { navController.popBackStack() })
        }
    }
}