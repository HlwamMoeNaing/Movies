package com.hmn.movies.Network

import com.hmn.movies.Model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Retro_service {
    @GET("popular?api_key=c11dcb567483000f07d05199bf19ef01" )
    fun  getMovies(): Call<Movies_data>
    @GET("now_playing?api_key=c11dcb567483000f07d05199bf19ef01")
    fun getNowPlay():Call<NowPlay>
    @GET("top_rated?api_key=c11dcb567483000f07d05199bf19ef01")
    fun getTopRate():Call<TopRate>
    @GET("upcoming?api_key=c11dcb567483000f07d05199bf19ef01")
    fun upCom():Call<Upcoming>

    @GET("{movie_id}?api_key=c11dcb567483000f07d05199bf19ef01")
    fun getMovieDetails(@Path("movie_id") id: Int): Call<MovieDetails>


}