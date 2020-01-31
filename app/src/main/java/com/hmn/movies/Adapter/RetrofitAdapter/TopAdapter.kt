package com.hmn.movies.Adapter.RetrofitAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hmn.movies.Activity.DetailActivity
import com.hmn.movies.Model.Result3
import com.hmn.movies.R
import com.squareup.picasso.Picasso

class TopAdapter(val ctx: Context, val list: List<Result3>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val my_view = LayoutInflater.from(ctx).inflate(R.layout.movies_list, parent, false)
        return Top_Rate(my_view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Top_Rate).bind(
            list[position].title,
            list[position].release_date,
            list[position].poster_path
        )
        holder.itemView.setOnClickListener {
            val i = Intent(ctx, DetailActivity::class.java)
            i.putExtra("id", list[position].id)
            ctx.startActivity(i)

        }
    }

    class Top_Rate(view: View) : RecyclerView.ViewHolder(view) {
        val tittle = view.findViewById<TextView>(R.id.movie_title)
        val releaseDate = view.findViewById<TextView>(R.id.movie_release_date)
        val poster = view.findViewById<ImageView>(R.id.movie_poster)

        fun bind(t: String, r: String, p: String) {
            tittle.text = t
            releaseDate.text = r
            val posterUrl = "https://image.tmdb.org/t/p/w500/" + p
            Picasso.with(itemView.context).load(posterUrl).into(poster)

        }
    }
}