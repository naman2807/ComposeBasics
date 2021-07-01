package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
               MyScreenContent()
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
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

//To add internal state to a composable, use the mutableStateOf function,
// which gives a composable mutable memory. To not have a different state for every
// , remember the mutable state using remember. And, if there are multiple instances of
// the composable at different places on the screen, each copy will get its own version
// of the state. You can think of internal state as a private variable in a class.
@Composable
fun Counter(){
    val count = remember { mutableStateOf(0) }
    Button(onClick = { count.value++ }) {
        Text("I've been clicked ${count.value} times")
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "There")){
    Column{
       for(name in names){
            Greeting(name = name)
           Divider(color = Color.Black)
       }
        Divider(color = Color.Transparent, thickness = 32.dp)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        MyScreenContent()
    }
}