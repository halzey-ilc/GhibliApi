package com.example.ghibliapi.data.network

import com.example.ghibliapi.data.model.Film
import retrofit2.http.GET
import retrofit2.http.Query

interface GhibiApi {

    @GET("films/")
    suspend fun getAllMovies(
        @Query("fields") field: String = "",
        @Query("limit") limit: Int = 0
    ): List<Film>
}