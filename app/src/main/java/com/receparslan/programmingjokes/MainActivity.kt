package com.receparslan.programmingjokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.receparslan.programmingjokes.screens.JokeListScreen
import com.receparslan.programmingjokes.ui.theme.ProgrammingJokesTheme
import com.receparslan.programmingjokes.viewmodel.JokeViewModel

class MainActivity : ComponentActivity() {

    @Suppress("unused", "RedundantSuppression")
    private val viewModel: JokeViewModel by viewModels<JokeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProgrammingJokesTheme {
                JokeListScreen(viewModel)
            }
        }
    }
}