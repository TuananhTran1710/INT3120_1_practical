package com.example.sports.ui

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sports.R
import com.example.sports.data.LocalSportsDataProvider
import com.example.sports.model.Sport
import com.example.sports.ui.theme.SportsTheme

enum class SportsContentType { LIST_ONLY, LIST_AND_DETAIL }

/**
 * Main composable that serves as container
 * which displays content according to [uiState] and [windowSize]
 */
@Composable
fun SportsApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    val viewModel: SportsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            SportsAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
            )
        }
    ) { innerPadding ->
        val contentType = when (windowSize) {
            WindowWidthSizeClass.Compact -> SportsContentType.LIST_ONLY
            WindowWidthSizeClass.Medium -> SportsContentType.LIST_ONLY
            WindowWidthSizeClass.Expanded -> SportsContentType.LIST_AND_DETAIL
            else -> SportsContentType.LIST_ONLY
        }

        when (contentType) {
            SportsContentType.LIST_ONLY -> {
                if (uiState.isShowingListPage) {
                    val context = LocalContext.current
                    SportsList(
                        sports = uiState.sportsList,
                        selectedSports = uiState.selectedSports,
                        onClick = {
                            viewModel.updateCurrentSport(it)
                            viewModel.navigateToDetailPage()
                        },
                        onCheckedChange = { sport, checked ->
                            viewModel.toggleSportSelection(sport, checked)
                        },
                        onNextClick = {
                            val intent = Intent(context, com.example.sports.SelectedSportsActivity::class.java)
                            intent.putParcelableArrayListExtra(
                                "selectedSports",
                                ArrayList(uiState.selectedSports)
                            )
                            context.startActivity(intent)
                        },
                        contentPadding = innerPadding,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(R.dimen.padding_medium),
                                start = dimensionResource(R.dimen.padding_medium),
                                end = dimensionResource(R.dimen.padding_medium),
                            )
                    )
                } else {
                    SportsDetail(
                        selectedSport = uiState.currentSport,
                        contentPadding = innerPadding,
                        onBackPressed = { viewModel.navigateToListPage() }
                    )
                }
            }
            SportsContentType.LIST_AND_DETAIL -> {
                val context = LocalContext.current
                SportsListAndDetail(
                    sports = uiState.sportsList,
                    selectedSport = uiState.currentSport,
                    onClick = { viewModel.updateCurrentSport(it) },
                    onBackPressed = onBackPressed,
                    contentPadding = innerPadding,
                    // thêm nút Next ở cột trái (list)
                    selectedSports = uiState.selectedSports,
                    onCheckedChange = { sport, checked ->
                        viewModel.toggleSportSelection(sport, checked)
                    },
                    onNextClick = {
                        val intent = Intent(context, com.example.sports.SelectedSportsActivity::class.java)
                        intent.putParcelableArrayListExtra(
                            "selectedSports",
                            ArrayList(uiState.selectedSports)
                        )
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

/**
 * AppBar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = if (!isShowingListPage) {
                    stringResource(R.string.detail_fragment_label)
                } else {
                    stringResource(R.string.list_fragment_label)
                }
            )
        },
        navigationIcon = if (!isShowingListPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SportsListItem(
    sport: Sport,
    onItemClick: (Sport) -> Unit,
    onCheckedChange: (Sport, Boolean) -> Unit,
    isChecked: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(sport) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            SportsListImageItem(
                sport = sport,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(sport.titleResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(sport.subtitleResourceId),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                // Hiển thị calo
                Text(
                    text = "${sport.calories} calo",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                Spacer(Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = pluralStringResource(
                            R.plurals.player_count_caption,
                            sport.playerCount,
                            sport.playerCount
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.weight(1f))
                    if (sport.olympic) {
                        Text(
                            text = stringResource(R.string.olympic_caption),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked -> onCheckedChange(sport, checked) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SportsListImageItem(sport: Sport, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(sport.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun SportsList(
    sports: List<Sport>,
    selectedSports: Set<Sport>,
    onClick: (Sport) -> Unit,
    onCheckedChange: (Sport, Boolean) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            modifier = modifier.weight(1f),
        ) {
            items(sports, key = { sport -> sport.id }) { sport ->
                SportsListItem(
                    sport = sport,
                    onItemClick = onClick,
                    onCheckedChange = onCheckedChange,
                    isChecked = selectedSports.contains(sport)
                )
            }
        }

        // Nút Next
        Button(
            onClick = onNextClick,
            enabled = selectedSports.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(text = stringResource(id = R.string.next_button_text))
        }
    }
}

@Composable
private fun SportsDetail(
    selectedSport: Sport,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackPressed() }

    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Image(
                    painter = painterResource(selectedSport.sportsImageBanner),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth,
                )
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {
                    Text(
                        text = stringResource(selectedSport.titleResourceId),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                    Row(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    ) {
                        Text(
                            text = pluralStringResource(
                                R.plurals.player_count_caption,
                                selectedSport.playerCount,
                                selectedSport.playerCount
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                        Spacer(Modifier.weight(1f))
                        if (selectedSport.olympic) {
                            Text(
                                text = stringResource(R.string.olympic_caption),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                            )
                        }
                    }
                }
            }
            // Nội dung + calo
            Text(
                text = stringResource(selectedSport.sportDetails),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = stringResource(R.string.calories_label, selectedSport.calories),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal),
                    vertical = 8.dp
                )
            )
        }
    }
}

@Composable
private fun SportsListAndDetail(
    sports: List<Sport>,
    selectedSport: Sport,
    onClick: (Sport) -> Unit,
    onBackPressed: () -> Unit,
    selectedSports: Set<Sport>,
    onCheckedChange: (Sport, Boolean) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Row(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1F)
                .padding(
                    top = dimensionResource(R.dimen.padding_medium),
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                )
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = contentPadding.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                modifier = Modifier.weight(1f)
            ) {
                items(sports, key = { it.id }) { sport ->
                    SportsListItem(
                        sport = sport,
                        onItemClick = onClick,
                        onCheckedChange = onCheckedChange,
                        isChecked = selectedSports.contains(sport)
                    )
                }
            }
            Button(
                onClick = onNextClick,
                enabled = selectedSports.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) { Text(text = stringResource(id = R.string.next_button_text)) }
        }

        SportsDetail(
            selectedSport = selectedSport,
            onBackPressed = onBackPressed,
            contentPadding = PaddingValues(top = contentPadding.calculateTopPadding()),
            modifier = Modifier.weight(2F)
        )
    }
}

///* --- Previews (không bắt buộc, giữ nguyên nếu bạn muốn) --- */
//@Composable
//@Preview
//fun SportsListItemPreview() {
//    SportsTheme {
//        SportsListItem(
//            sport = LocalSportsDataProvider.defaultSport,
//            onItemClick = {},
//            onCheckedChange = { _, _ -> },
//            isChecked = true
//        )
//    }
//}
