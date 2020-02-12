package com.hmn.movies.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.hmn.movies.Model.MovieDetails
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.movie_release_date
import kotlinx.android.synthetic.main.movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val movieId: Int = intent.getIntExtra("id",0)

        getMovieDetail(movieId)

    }

    private fun getMovieDetail(id:Int){
        getRetrofit<Retro_service>().getMovieDetails(id)
            .enqueue(object :Callback<MovieDetails>{
                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    Log.e("@dt","Fail")
                }

                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
                    if (response.isSuccessful){
                        Log.e("@dt","Success")
                        val data = response.body()!!

                        movie_tagline.text = data.tagline
                        tvd_title.text = data.title
                        movie_release_date.text = data.releaseDate
                        movie_rating.text = "Rating- "+data.rating.toString()
                        movie_runtime.text = data.runtime.toString() +" -Minutes"
                        movie_budget.text = "Budget -"+data.budget.toString()
                        movie_revenue.text ="Revenue -"+ data.revenue.toString()
                        movie_overview.text = data.overview
                        val posterUrl = "https://image.tmdb.org/t/p/w500/" + data.posterPath
                       // Picasso.with(this@DetailActivity).load(posterUrl).into(iv_movie_poster)
                        // val moviePosterURL = POSTER_BASE_URL + it.posterPath
                        Glide.with(this@DetailActivity)
                            .load(posterUrl)
                            .into(iv_movie_poster)



//                        val backUrl ="https://image.tmdb.org/t/p/w500/" + data.backdrop_path
//                        Picasso.with(this@DetailActivity).load(backUrl).into(cover)


                    }
                }

            })
    }
}
