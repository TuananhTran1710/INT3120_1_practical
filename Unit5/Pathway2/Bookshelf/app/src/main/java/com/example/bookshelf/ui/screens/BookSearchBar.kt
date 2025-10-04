package com.example.bookshelf.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachReversed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchBar(
    searchAction: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchItems: SnapshotStateList<String> = remember { mutableStateListOf() }
    if (searchItems.size > 4) searchItems.removeFirst()

    DockedSearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            active = false
            searchAction(query)
            searchItems.add(query)
        },
        active = active,
        onActiveChange = {
            active = it
        },
        leadingIcon = {
            when (active) {
                false -> Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )

                true -> Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        active = false
                    }
                )
            }
        },
        placeholder = {
            Text(
                text = "Search for books"
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        query = ""
                    },
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        searchItems.fastForEachReversed {
            Row(modifier = Modifier.padding(14.dp)) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove search entry",
                    modifier = Modifier.clickable {
                        searchItems.remove(it)
                    }
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = it)
            }
        }
    }
}