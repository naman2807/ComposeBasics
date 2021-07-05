package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

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
fun Counter(count: Int, updateCount: (Int) -> Unit){
    Button(onClick = { updateCount(count + 1) },
            colors = ButtonDefaults.buttonColors(backgroundColor =
            if(count > 5) Color.Green else Color.White)) {

        Text("I've been clicked ${count} times")
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }){
    val counterState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()){
        NameList(names = names, Modifier.weight(1f))
        Counter(count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            })
    }
}

//LazyColumn renders only the visible items on screen, allowing performance gains when
// rendering a big list. It is equivalent to RecyclerView in Android Views.
//LazyColumn doesn't recycle its children like RecyclerView. It emits new Composables
// as you scroll through it and is still performant as emitting Composables is relatively
// cheap compared to instantiating Android Views.
@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { item ->
            Greeting(name = item)
            Divider(color = Color.Black)
        }
    }
}

//animateColorAsState takes a Color as a parameter, saves it and generates automatically
// the intermediate colors required to display an animated transition from the previously
// set color to the new one.
@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
    Text(text = "Hello $name!", modifier = Modifier
        .padding(24.dp)
        .background(color = backgroundColor)
        .clickable(onClick = { isSelected = !isSelected }))
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        MyScreenContent()
    }
}