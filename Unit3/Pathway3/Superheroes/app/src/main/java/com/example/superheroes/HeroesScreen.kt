package com.example.superheroes

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

@Composable
fun SuperheroesList(heroes: List<Hero>, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    LazyColumn(contentPadding = contentPadding) {
        items(heroes) { hero ->
            SuperheroListItem(hero)
        }
    }
}

@Composable
fun SuperheroListItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(
                horizontal = 16.dp, vertical = 8.dp
            ), elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(72.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.size(72.dp)
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    modifier = Modifier.clip(shape = MaterialTheme.shapes.small)
                )
            }
        }
    }
}

//@Preview("Light Theme")
//@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun SuperheroPreview() {
//    SuperheroesTheme {
//        SuperheroListItem(hero = HeroesRepository.heroes[0])
//    }
//}

@Preview("Heroes List", showSystemUi = true)
@Composable
fun HeroesPreview() {
    SuperheroesTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SuperheroesList(heroes = HeroesRepository.heroes)
        }
    }
}