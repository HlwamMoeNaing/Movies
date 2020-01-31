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
import com.hmn.movies.Model.Result4
import com.hmn.movies.R
import com.squareup.picasso.Picasso

class UpcomingAdapter(val ctc:Context,val list: List<Result4>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val my_view =LayoutInflater.from(ctc).inflate(R.layout.movies_list,parent,false)
        return MyUp(
            my_view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyUp).bind(list[position].title,list[position].release_date,list[position].poster_path)
        holder.itemView.setOnClickListener {
            val i = Intent(ctc, DetailActivity::class.java)
            i.putExtra("id", list[position].id)
            ctc.startActivity(i)

        }
    }
    class MyUp(v: View):RecyclerView.ViewHolder(v){
        val tittle = v.findViewById<TextView>(R.id.movie_title)
        val releaseDate = v.findViewById<TextView>(R.id.movie_release_date)
        val poster = v.findViewById<ImageView>(R.id.movie_poster)

        fun bind(t:String,r:String,p:String){
            tittle.text = t
            releaseDate.text=r
            val posterUrl = "https://image.tmdb.org/t/p/w500/"+p
            Picasso.with(itemView.context).load(posterUrl).into(poster)

        }
    }
}