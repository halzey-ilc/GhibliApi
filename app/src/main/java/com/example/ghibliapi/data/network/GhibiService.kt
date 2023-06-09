package com.example.ghibliapi.data.network

import com.example.ghibliapi.data.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GhibiService @Inject constructor(private val api: GhibiApi) {
    suspend fun getAllMovies(): List<Film>{
        return  withContext(Dispatchers.IO){
            api.getAllMovies()
        }
    }
}