package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.R
import com.example.mycity.data.CategoryData
import com.example.mycity.data.PlaceData
import com.example.mycity.model.CategoryItem
import com.example.mycity.model.ListItem
import com.example.mycity.model.PlaceItem
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun CardListItem(
    item: ListItem,
    onItemClick: (ListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(item) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            ListItemImage(
                listItem = item,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                horizontalAlignment =
                    if (item is CategoryItem) Alignment.CenterHorizontally
                    else Alignment.Start,
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(item.name),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                if (item is PlaceItem) {
                    Text(
                        text = stringResource(item.address),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3
                    )
                }
            }
        }
    }
}

@Composable
private fun ListItemImage(
    listItem: ListItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(listItem.img),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun CategoryListItemPreview() {
    MyCityTheme {
        CardListItem(
            item = CategoryData.getCategoryList()[0],
            onItemClick = {},
        )
    }
}

@Preview
@Composable
fun PlaceListItemPreview() {
    MyCityTheme {
        CardListItem(
            item = PlaceData.getCafeList()[0],
            onItemClick = {}
        )
    }
}
