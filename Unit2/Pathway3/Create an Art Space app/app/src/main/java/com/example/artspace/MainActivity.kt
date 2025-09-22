package com.example.artspace

import android.R.attr.text
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var order by remember { mutableStateOf(1) }

    val image = when(order){
        1 -> R.drawable.first
        2 -> R.drawable.second
        else -> R.drawable.third

    }

    val title = when(order){
        1 -> R.string.title_1
        2 -> R.string.title_2
        else -> R.string.title_3

    }

    val artist = when(order){
        1 -> R.string.artist_1
        2 -> R.string.artist_2
        else -> R.string.artist_3

    }

    val year = when(order){
        1 -> R.string.year_1
        2 -> R.string.year_2
        else -> R.string.year_3
    }



    Column (modifier = Modifier.padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(100.dp))


        val imageResource = painterResource(image)

        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth().height(220.dp).offset(x = 0.dp, y = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),

        ) {
            Image(
                painter = imageResource,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))


        Card(
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),

            ) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = stringResource(title),
                modifier.padding(start = 15.dp),
                fontSize = 24.sp)
            Row(modifier.padding(start = 15.dp)) {
                Text(
                    text = stringResource(artist),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "(" + stringResource(year) + ")")
            }
            Spacer(modifier = Modifier.height(5.dp))
        }

        Spacer(modifier = Modifier.height(350.dp))

        Row(modifier = Modifier.padding()){
            Button(onClick = {
                if(order == 1){
                    order = 3
                }
                else order--
            }, modifier = Modifier.width(130.dp)
                ) {
                Text(text = stringResource(R.string.previous),
                    fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(90.dp))

            Button(onClick = {
                if(order == 3){
                    order = 1
                }
                else order++
            }, modifier = Modifier.width(130.dp)) {
                Text(text = stringResource(R.string.next), fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}