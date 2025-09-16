package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var order by remember { mutableStateOf(1) }
    var count by remember { mutableStateOf(1) }
    var result by remember { mutableStateOf(4) }
    val stringVal = when(order){
        1 -> R.string.first
        2 -> R.string.second
        3 -> {
            if(count != result) R.string.second
            else R.string.third
        }
        else -> R.string.four
    }

    val image = when(order){
        1 -> R.drawable.lemon_tree
        2 -> {
            R.drawable.lemon_squeeze}
        3 ->
        {

                R.drawable.lemon_drink

        }
        else -> R.drawable.lemon_restart
    }


    Column (modifier = Modifier.fillMaxSize().padding(7.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        val imageResource = painterResource(image)
        Button(onClick = {
            if(order == 1)
            {
                count = 0
                result = (2..4).random()
                order++
            }
            else if(order == 2)
            {
                if(count < result) count++
                if(count == result) order++
            }
            else order++
            if(order == 5) order = 1

        },
            shape = RoundedCornerShape(40.dp) ) {
            Image(
                painter = imageResource,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(stringVal),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Greeting()
    }
}