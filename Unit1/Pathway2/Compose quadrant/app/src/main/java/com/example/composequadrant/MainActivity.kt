package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Surface (
                    modifier= Modifier.fillMaxSize(),
                    color= MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column (Modifier.fillMaxSize())
    {
        Row (Modifier.weight((1f))){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(16.dp).background(Color(0xFFEADDFF)), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,


            ){
                Text(text= stringResource(R.string.Text_composable),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp))

                Text(text = stringResource(R.string.text_content),
                    textAlign = TextAlign.Justify
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(16.dp).background(Color(0xFFD0BCFF)), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,


                ){
                Text(text= stringResource(R.string.Image_composable),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp))

                Text(text = stringResource(R.string.image_content),
                    textAlign = TextAlign.Justify
                )
            }

        }

        Row(Modifier.weight(1f)){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(16.dp).background(Color(0xFFB69DF8)), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,


                ){
                Text(text= stringResource(R.string.Row_composable),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp))

                Text(text = stringResource(R.string.row_content),
                    textAlign = TextAlign.Justify
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(16.dp).background(Color(0xFFF6EDFF)), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,


                ){
                Text(text= stringResource(R.string.Column_composable),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp))

                Text(text = stringResource(R.string.column_content),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Greeting()
    }
}