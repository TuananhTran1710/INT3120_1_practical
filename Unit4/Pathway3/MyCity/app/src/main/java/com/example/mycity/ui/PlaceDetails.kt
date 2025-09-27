package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.PlaceData
import com.example.mycity.model.PlaceItem
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun PlaceDetails(
    selectedPlaceItem: PlaceItem?,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    if (selectedPlaceItem == null) {
        return
    }
    BackHandler {
        onBackPressed()
    }
    Column (
        modifier = modifier
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
        ) {
            Image(
                painter = painterResource(selectedPlaceItem.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = stringResource(selectedPlaceItem.name),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    .padding(top = dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = "Address: " + stringResource(selectedPlaceItem.address),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = stringResource(selectedPlaceItem.desc),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailsPreview() {
    MyCityTheme {
        PlaceDetails(
            selectedPlaceItem = PlaceData.getCafeList()[0],
            onBackPressed = {},
        )
    }
}