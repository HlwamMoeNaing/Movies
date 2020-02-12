package com.hmn.movies.ui.RetrofitFrag


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.hmn.movies.Adapter.RetrofitAdapter.RecyclerAdapter
import com.hmn.movies.Model.Movies_data
import com.hmn.movies.R
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.RoomDatabase.Database.DemoClient

import com.hmn.movies.RoomDatabase.Entity.MoviesEntity

import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.StringBufferInputStream

/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment(),SearchView.OnQueryTextListener {
lateinit var adapter:RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMovies()
        val search = activity!!.findViewById<SearchView>(R.id.populer_search)
        search.setOnQueryTextListener(this)
    }


    private fun getMovies() {
        getRetrofit<Retro_service>().getMovies()
            .enqueue(object : Callback<Movies_data> {
                override fun onFailure(call: Call<Movies_data>, t: Throwable) {
                    Toast.makeText(activity, "Failed to Response", Toast.LENGTH_LONG).show()
                    Log.e("@hmn", "Fail")
                }

                override fun onResponse(call: Call<Movies_data>, response: Response<Movies_data>) {
                    var tittle: String
                    var r_date: String
                    var poster_path: String
                    if (response.isSuccessful) {
                        Log.e("@hmn", "success")
                        val data = response.body()!!
                        val list = data.results
                        fb_popular_down.setOnClickListener {
                            for (i in list) {
                                tittle = i.title
                                r_date = i.release_date
                                poster_path = i.poster_path
                                saveMovie(tittle, r_date, poster_path)
                            }
                        }
                        val Gmgr = GridLayoutManager(activity!!, 3)
                         adapter =
                            RecyclerAdapter(
                                activity!!,
                                list
                            )
                        rv_populer.layoutManager = Gmgr
                        rv_populer.adapter = adapter
                    }
                }

            })
    }

    private fun saveMovie(t: String, r: String, p: String) {
        class SaveMovies : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val movies = MoviesEntity()
                movies.tittle = t
                movies.release_date = r
                movies.poster_path = p
                DemoClient.getDemoInstance(activity!!).demoDb.demoDao().demoInsert(movies)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity!!, "Successfully Saved", Toast.LENGTH_SHORT).show()


            }

        }

        val sm = SaveMovies()
        sm.execute()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
       adapter.filter.filter(p0)
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        adapter.filter.filter(p0)
        return false
    }


}
