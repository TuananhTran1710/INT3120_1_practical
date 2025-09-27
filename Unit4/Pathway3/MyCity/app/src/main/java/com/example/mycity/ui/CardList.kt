package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.model.ListItem

@Composable
fun CardList(
    list: List<ListItem>,
    onClick: (ListItem) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    BackHandler {
        onBackPressed()
    }
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_small)),
    ) {
        items(list) { item ->
            CardListItem(
                item = item,
                onItemClick = onClick
            )
        }
    }
}