package com.example.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BookGridScreen
import com.example.bookshelf.ui.screens.BookSearchBar
import com.example.bookshelf.ui.screens.ErrorScreen
import com.example.bookshelf.ui.screens.LoadingScreen
import com.example.bookshelf.ui.screens.NoResultScreen
import com.example.bookshelf.ui.viewmodel.BooksUiState
import com.example.bookshelf.ui.viewmodel.BookshelfViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val bookshelfViewModel: BookshelfViewModel =
                viewModel(factory = BookshelfViewModel.Factory)
            val booksUiState = bookshelfViewModel.booksUiState
            var query by remember { mutableStateOf("") }

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(top = contentPadding.calculateTopPadding())
                    .padding(horizontal = 16.dp)
            ) {
                BookSearchBar(
                    searchAction = {
                        query = it
                        bookshelfViewModel.getBooks(query)
                    },
                    modifier = Modifier.fillMaxSize(),
                )
                when (booksUiState) {
                    is BooksUiState.Search -> {}
                    is BooksUiState.Error ->
                        ErrorScreen(
                            retryAction = { bookshelfViewModel.getBooks(query) },
                            modifier = Modifier
                                .padding(contentPadding.calculateTopPadding())
                        )

                    is BooksUiState.Loading -> LoadingScreen(Modifier.fillMaxSize())
                    is BooksUiState.Success ->
                        BookGridScreen(
                            books = booksUiState.books,
                            contentPadding = contentPadding
                        )

                    is BooksUiState.NoResult -> NoResultScreen(Modifier.fillMaxSize())
                }
            }
        }
    }
}




