package com.example.a30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30days.model.Plant
import com.example.a30days.model.PlantType
import com.example.a30days.model.plants
import com.example.a30days.ui.theme.AppTheme
import com.example.a30days.ui.theme.cardColorAquatic
import com.example.a30days.ui.theme.cardColorDay
import com.example.a30days.ui.theme.cardColorNight
import com.example.a30days.ui.theme.cardColorNightAquatic
import com.example.a30days.ui.theme.cardColorRoof
import com.example.a30days.ui.theme.innerColor
import com.example.a30days.ui.theme.outerColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                AppTheme {
                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                        App()
                    }
                }
        }
    }
}

@Composable
fun App() {
    Scaffold(
        topBar = {
            AppTopBar()
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = outerColor
    ) { innerPadding ->
        PlantList(plants, innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Plants Almanac",
                style = MaterialTheme.typography.displayMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(outerColor)
    )
}

@Composable
fun PlantList(plants: List<Plant>, contentPadding: PaddingValues = PaddingValues(0.dp)) {

    LazyColumn(
        modifier = Modifier
            .background(innerColor)
            .padding(vertical = 8.dp),
        contentPadding = contentPadding,
    ) {
        items(plants) { plant ->
            PlantItem(plant)
        }
    }
}

@Composable
fun PlantItem(plant: Plant, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card (
        onClick = { expanded = !expanded },
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(18.dp),
        colors = when (plant.type) {
            PlantType.Day -> CardDefaults.cardColors(cardColorDay)
            PlantType.Night -> CardDefaults.cardColors(cardColorNight)
            PlantType.Aquatic -> CardDefaults.cardColors(cardColorAquatic)
            PlantType.NightAquatic -> CardDefaults.cardColors(cardColorNightAquatic)
            PlantType.Roof -> CardDefaults.cardColors(cardColorRoof)
        },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ){
            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(plant.img),
                    contentDescription = stringResource(plant.name),
                    modifier = Modifier
                        .size(106.dp)
                )
                Spacer(modifier.width(10.dp))
                Column {
                    Text(
                        text = stringResource(plant.name),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = stringResource(plant.desc),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            if (expanded) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 12.dp, bottom = 12.dp, end = 12.dp)
                ) {
                    Text(
                        text = "Price: " + integerResource(plant.price)
                    )
                    Image(
                        painter = painterResource(R.drawable.sun),
                        contentDescription = "sun",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier.weight(1F))
                    Text(
                        text = "Recharge: " + plant.recharge
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    AppTheme {
        App()
    }
}