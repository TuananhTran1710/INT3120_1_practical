package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background) {
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.icon)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(modifier = Modifier.height(100.dp))
        Column (modifier = Modifier
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
         {
            Spacer(modifier = Modifier.height(7.dp))
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email icon",
                tint = Color.Black,
                modifier = Modifier.size(100.dp)
            )
             Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Full Name",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)
             Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Title", fontWeight = FontWeight.Bold)
        }


        Spacer(modifier = Modifier.height(200.dp))

        Column(modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(7.dp))
            Row(modifier = Modifier.padding(start = 100.dp)
               ,
                verticalAlignment = Alignment.CenterVertically,
                ){
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email icon",
                    tint = Color.Black,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(text = "0379002640")
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row(modifier = Modifier.padding(start = 100.dp)
               ,
                verticalAlignment = Alignment.CenterVertically,
                ){
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email icon",
                    tint = Color.Black,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(text = "@trantuananh")
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row(modifier = Modifier.padding(start = 100.dp)

                ,
                verticalAlignment = Alignment.CenterVertically,
                ){
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email icon",
                    tint = Color.Black,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(text = "23020011@vnu.edu.vn")
            }
            Spacer(modifier = Modifier.height(7.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting()
    }
}