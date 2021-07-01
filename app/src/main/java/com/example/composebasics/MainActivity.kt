package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MyApp {
               Greeting(name = "Android")
           }
        }
    }
}

//You return Unit because, as you might have noticed, Composable functions don't return
// UI components, they emit them. That's why they must return Unit
//Watch out for the extra parentheses in @Composable() when using a Composable function as
// a parameter. Since the annotation is applied on a function, they're needed!
@Composable
fun MyApp(content: @Composable () -> Unit){
    ComposeBasicsTheme {
        Surface {
            content()
        }
    }
}

@Composable
fun MyScreenContent(){

}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.Yellow) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        Greeting(name = "Android")
    }
}