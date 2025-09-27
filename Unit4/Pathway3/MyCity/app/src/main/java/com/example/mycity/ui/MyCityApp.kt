package com.example.mycity.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.data.CategoryData
import com.example.mycity.model.CategoryItem
import com.example.mycity.model.PlaceItem
import com.example.mycity.ui.theme.MyCityTheme

enum class MyCityAppContentType {
    ListOnly, ListAndDetail
}

enum class MyCityAppScreen {
    Categories,
    Recommendations,
    Details,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text("My City App") },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    val navController = rememberNavController()

    val viewModel: PlaceViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityAppScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityAppScreen.Categories.name
    )

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium
        -> MyCityAppContentType.ListOnly

        WindowWidthSizeClass.Expanded -> MyCityAppContentType.ListAndDetail
        else -> MyCityAppContentType.ListOnly
    }

    Scaffold(
        topBar = {
            MyCityTopAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        when (contentType) {
            MyCityAppContentType.ListOnly -> {
                NavHost(
                    navController = navController,
                    startDestination = MyCityAppScreen.Categories.name,
                ) {
                    composable(route = MyCityAppScreen.Categories.name) {
                        CardList(
                            list = CategoryData.getCategoryList(),
                            onClick = { categoryItem ->
                                viewModel.updatePlaceList(categoryItem as CategoryItem)
                                navController.navigate(MyCityAppScreen.Recommendations.name)
                            },
                            onBackPressed = onBackPressed,
                            modifier = Modifier,
                            contentPadding = innerPadding
                        )
                    }
                    composable(route = MyCityAppScreen.Recommendations.name) {
                        CardList(
                            list = uiState.placeList,
                            onClick = { placeItem ->
                                viewModel.updateCurrentPlace(placeItem as PlaceItem)
                                navController.navigate(MyCityAppScreen.Details.name)
                            },
                            modifier = Modifier,
                            onBackPressed = {
                                viewModel.updateCurrentList(CategoryData.getCategoryList())
                                navController.navigateUp()
                            },
                            contentPadding = innerPadding
                        )
                    }
                    composable(route = MyCityAppScreen.Details.name) {
                        PlaceDetails(
                            selectedPlaceItem = uiState.placeItem,
                            onBackPressed = {
                                navController.navigateUp()
                            },
                            modifier = Modifier
                                .padding(bottom = innerPadding.calculateBottomPadding())
                        )
                    }
                }
            }
            MyCityAppContentType.ListAndDetail -> {
                Column(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                        .padding(bottom = innerPadding.calculateBottomPadding())
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                        modifier = Modifier
                            .padding(top = innerPadding.calculateTopPadding()),
                    ) {
                        for(categoryItem in CategoryData.getCategoryList()) {
                            CardListItem(
                                item = categoryItem,
                                onItemClick = {
                                    viewModel.updatePlaceList(it as CategoryItem)
                                },
                                modifier = Modifier.weight(1F)
                            )
                        }
                    }
                    Row(
                        Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
                    ) {
                        CardList(
                            list = uiState.placeList,
                            onClick = { placeItem ->
                                viewModel.updateCurrentPlace(placeItem as PlaceItem)
                            },
                            onBackPressed = onBackPressed,
                            modifier = Modifier
                                .fillMaxWidth(0.33F)
                        )
                        PlaceDetails(
                            selectedPlaceItem = uiState.placeItem,
                            onBackPressed = onBackPressed,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CompactPreview() {
    MyCityTheme {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Compact
        ){}
    }
}

@Preview(widthDp = 1000)
@Composable
fun ExpandedPreview() {
    MyCityTheme {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Expanded
        ){}
    }
}



