package com.receparslan.programmingjokes.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.receparslan.programmingjokes.model.Joke
import com.receparslan.programmingjokes.services.JokeAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel() : ViewModel() {

    var jokeList = mutableStateOf<List<Joke>>(emptyList()) // List of jokes to be displayed in the UI

    init {
        getAllJokes() // Fetch jokes when the ViewModel is initialized
    }

    fun getAllJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = JokeAPI.retrofitService.getJAllJoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        jokeList.value = it.jokes
                    }
                }
            } catch (_: Exception) {
                jokeList.value = listOf<Joke>(Joke("single", "Check your connection!", "", "", 0))
            }
        }
    }
}