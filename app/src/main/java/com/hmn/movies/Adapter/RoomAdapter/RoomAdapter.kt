package com.hmn.movies.Adapter.RoomAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Entity.MoviesEntity
import com.squareup.picasso.Picasso

class RoomAdapter(val ctx:Context,val list: List<MoviesEntity>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.movies_list, parent, false)
        return MyMoviesDb(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyMoviesDb).bind(
            list[position].tittle!!,
            list[position].release_date!!,
            list[position].poster_path!!
        )
    }

    class MyMoviesDb(v: View) : RecyclerView.ViewHolder(v) {
        val tittle = v.findViewById<TextView>(R.id.movie_title)
        val releaseDate = v.findViewById<TextView>(R.id.movie_release_date)
        val poster = v.findViewById<ImageView>(R.id.movie_poster)

        fun bind(t: String, r: String, p: String) {
            tittle.text = t
            releaseDate.text = r
            val posterUrl = "https://image.tmdb.org/t/p/w500/" + p
            Picasso.with(itemView.context).load(posterUrl).into(poster)
        }
    }
}