package com.example.ghibliapi.domain

import com.example.ghibliapi.data.GhibiRepository
import com.example.ghibliapi.data.model.Film
import javax.inject.Inject

class GhibiUseCases @Inject constructor(private val repository: GhibiRepository) {
    suspend operator fun invoke(): List<Film> = repository.getAllMovies()
}