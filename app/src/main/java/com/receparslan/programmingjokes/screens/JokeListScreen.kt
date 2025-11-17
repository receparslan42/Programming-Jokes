package com.receparslan.programmingjokes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.receparslan.programmingjokes.model.Joke
import com.receparslan.programmingjokes.viewmodel.JokeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeListScreen(viewModel: JokeViewModel) {

    val jokeList = viewModel.jokeList.value // Joke list is observed from the view model

    // State to manage the refreshing state
    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        @Suppress("AssignedValueIsNeverRead") // isRefreshing is not used directly but is required for the PullToRefreshBox
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                coroutineScope.launch {
                    viewModel.getAllJokes()
                    delay(1500)
                    isRefreshing = false
                }
            }
        ) {
            LazyColumn(Modifier.padding(innerPadding)) {
                items(jokeList) {
                    JokeListRow(it)
                }
            }
        }
    }
}

@Composable
fun JokeListRow(joke: Joke) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 3.dp)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(8.dp)),
    ) {
        // Display the joke type and joke text based on the type of joke
        when (joke.type) {
            "single" -> {
                LightText(joke.joke)
            }

            "twopart" -> {
                Column(Modifier.padding(3.dp)) {
                    Row {
                        BoldText("Setup     : ")
                        LightText(joke.setup)
                    }

                    Spacer(Modifier.padding(3.dp))

                    Row {
                        BoldText("Delivery : ")
                        LightText(joke.delivery)
                    }
                }
            }
        }
    }
}

@Composable
fun BoldText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        ),
        modifier = Modifier.padding(3.dp)
    )
}

@Composable
fun LightText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.DarkGray
        ),
        modifier = Modifier.padding(3.dp)
    )
}