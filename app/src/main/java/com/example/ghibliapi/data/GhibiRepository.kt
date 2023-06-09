package com.example.ghibliapi.data

import com.example.ghibliapi.data.model.Film
import com.example.ghibliapi.data.network.GhibiService
import javax.inject.Inject

class GhibiRepository @Inject constructor(
    private val service: GhibiService
) {
    suspend fun getAllMovies(): List<Film>{
        return service.getAllMovies()
    }
}