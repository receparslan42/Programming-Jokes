package com.receparslan.programmingjokes.model

data class Jokes(var jokes: List<Joke>)
data class Joke(var type: String, var joke: String, var setup: String, var delivery: String, var id: Int)

