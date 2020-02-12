package com.hmn.movies.Adapter.RetrofitAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.hmn.movies.Activity.DetailActivity
import com.hmn.movies.R
import com.hmn.movies.Model.Result2
import com.squareup.picasso.Picasso

class NowPlayAdapter(val ctx: Context, val list: List<Result2>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    internal var filterListResult: List<Result2>

    init {
        this.filterListResult = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val my_view = LayoutInflater.from(ctx).inflate(R.layout.movies_list, parent, false)
        return NowPlay(
            my_view
        )
    }

    override fun getItemCount(): Int {
        return filterListResult.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NowPlay).bind(
            filterListResult[position].title,
            filterListResult[position].release_date,
            filterListResult[position].poster_path
        )
        holder.itemView.setOnClickListener {
            val i = Intent(ctx, DetailActivity::class.java)
            i.putExtra("id", list[position].id)
            ctx.startActivity(i)

        }
    }

    class NowPlay(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charString: CharSequence?): FilterResults {
                val charSearch = charString.toString()
                if (charSearch.isEmpty())
                    filterListResult = list

                else {
                    val resultList = ArrayList<Result2>()
                    for (row in list) {

                        if (row.title.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)

                    }
                    filterListResult = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterListResult
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filterListResult = p1!!.values as List<Result2>
                notifyDataSetChanged()
            }

        }
    }
}