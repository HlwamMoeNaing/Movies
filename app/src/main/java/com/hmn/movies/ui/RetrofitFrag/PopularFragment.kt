package com.hmn.movies.ui.RetrofitFrag


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.hmn.movies.Adapter.RetrofitAdapter.RecyclerAdapter
import com.hmn.movies.Model.Movies_data
import com.hmn.movies.R
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.RoomDatabase.Popular.DatabaseClient
import com.hmn.movies.RoomDatabase.Popular.MoviesEntity
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
    }

    private fun getMovies() {
        getRetrofit<Retro_service>().getMovies()
            .enqueue(object : Callback<Movies_data> {
                override fun onFailure(call: Call<Movies_data>, t: Throwable) {
                    Toast.makeText(activity, "Failed to Response", Toast.LENGTH_LONG).show()
                    Log.e("@hmn", "Fail")
                }

                override fun onResponse(call: Call<Movies_data>, response: Response<Movies_data>) {
                    if (response.isSuccessful) {
                        Log.e("@hmn", "success")
                        val data = response.body()!!
                        val i = data.results
                        for (j in i) {
                            val tit = j.title
                            val rel_dat = j.release_date
                            val pos_pat = j.poster_path
                            println(tit)
                            println(rel_dat)
                            println(pos_pat)
                            saveMovie(tit,rel_dat,pos_pat)
                        }


                        val list = data.results
                        val Gmgr = GridLayoutManager(activity!!, 3)
                        val adapter =
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


    private fun saveMovie(titt: String, r_date: String, post_path: String) {


        class SaveMovie : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val movie = MoviesEntity()
                movie.tittle = titt
                movie.release_date = r_date
                movie.poster_path = post_path
                DatabaseClient.getInstance(activity!!).moviesDatabase.moviesDao().insert(movie)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity, "Saved to Room", Toast.LENGTH_LONG).show()

            }

        }

        val sm = SaveMovie()
        sm.execute()

    }


}
