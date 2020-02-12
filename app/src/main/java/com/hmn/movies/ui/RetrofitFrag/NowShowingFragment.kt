package com.hmn.movies.ui.RetrofitFrag


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.hmn.movies.Adapter.RetrofitAdapter.NowPlayAdapter
import com.hmn.movies.Model.NowPlay
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Database.DemoClient

import com.hmn.movies.RoomDatabase.Entity.NowPlayEntity

import kotlinx.android.synthetic.main.fragment_now_showing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class NowShowingFragment : Fragment(),SearchView.OnQueryTextListener {
lateinit var adapter:NowPlayAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_showing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNowPlay()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val search =activity!!.findViewById<SearchView>(R.id.now_show_search)

        search.setOnQueryTextListener(this)

    }


    override fun onQueryTextSubmit(p0: String?): Boolean {
        adapter.filter.filter(p0)
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        adapter.filter.filter(p0)
        return false
    }


    private fun getNowPlay() {
        getRetrofit<Retro_service>().getNowPlay()
            .enqueue(object : Callback<NowPlay> {
                override fun onFailure(call: Call<NowPlay>, t: Throwable) {
                    Toast.makeText(activity, "Failed to Response", Toast.LENGTH_LONG).show()
                    Log.e("@np", "Fail")
                }

                override fun onResponse(call: Call<NowPlay>, response: Response<NowPlay>) {
                    var tittle: String
                    var r_date:String
                    var poster_path:String
                    if (response.isSuccessful) {
                        Log.e("@np", "success")
                        val data = response.body()!!
                        val list = data.results

                        fb_now_dowm.setOnClickListener {
                            for (i in list) {
                                tittle = i.title
                                r_date = i.release_date
                                poster_path = i.poster_path
                                saveNowPlay(tittle, r_date, poster_path)
                            }
                        }
                        val Gmgr = GridLayoutManager(activity!!, 3)
                        adapter =
                            NowPlayAdapter(
                                activity!!,
                                list
                            )
                        rv_nowplay.layoutManager = Gmgr
                        rv_nowplay.adapter = adapter
                    }
                }

            })
    }

    private fun saveNowPlay(t: String, r: String, p: String) {
        class SaveNowPlay : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val movies = NowPlayEntity()
                movies.tittle = t
                movies.release_date = r
                movies.poster_path = p
                DemoClient.getDemoInstance(activity!!).demoDb.demoDao().DemoInsertNplay(movies)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
             Toast.makeText(activity!!,"Successfully Saved",Toast.LENGTH_SHORT).show()
            }
        }

        val sn = SaveNowPlay()
        sn.execute()
    }



}
