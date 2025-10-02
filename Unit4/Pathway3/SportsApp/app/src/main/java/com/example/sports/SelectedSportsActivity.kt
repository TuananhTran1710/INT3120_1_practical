package com.example.sports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sports.model.Sport
import com.example.sports.ui.theme.SportsTheme

class SelectedSportsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedSports =
            intent.getParcelableArrayListExtra<Sport>("selectedSports") ?: arrayListOf()

        setContent {
            SportsTheme {
                Surface {
                    SelectedSportsScreen(selectedSports)
                }
            }
        }
    }
}

@Composable
fun SelectedSportsScreen(selectedSports: List<Sport>) {
    val totalCalories = selectedSports.sumOf { it.calories }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.selected_sports_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(selectedSports) { sport ->
                Column(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(
                        text = stringResource(id = sport.titleResourceId),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(R.string.calories_label, sport.calories),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Divider()
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.total_calories_label, totalCalories),
            style = MaterialTheme.typography.titleLarge
        )
    }
}
