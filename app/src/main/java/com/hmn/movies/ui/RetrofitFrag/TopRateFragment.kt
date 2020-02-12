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
import com.hmn.movies.Adapter.RetrofitAdapter.TopAdapter
import com.hmn.movies.Model.TopRate
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Database.DemoClient

import com.hmn.movies.RoomDatabase.Entity.TopRateEntity

import kotlinx.android.synthetic.main.fragment_top_rate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class TopRateFragment : Fragment(),SearchView.OnQueryTextListener {


    lateinit var adapter:TopAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getTopRete()
        val search = activity!!.findViewById<SearchView>(R.id.top_rate_search)
        search.setOnQueryTextListener(this)
    }

    private fun getTopRete() {
        getRetrofit<Retro_service>().getTopRate()
            .enqueue(object : Callback<TopRate> {
                override fun onFailure(call: Call<TopRate>, t: Throwable) {
                    Toast.makeText(activity, "Failed to Response", Toast.LENGTH_LONG).show()
                    Log.e("@tr", "Fail")
                }

                override fun onResponse(call: Call<TopRate>, response: Response<TopRate>) {
                    var tittle:String
                    var r_date:String
                    var poster_path:String

                    if (response.isSuccessful) {
                        Log.e("@tr", "success")
                        val data = response.body()!!
                        val list = data.results
                       fb_top_dowm.setOnClickListener {
                           for (i in list){
                               tittle = i.title
                               r_date = i.release_date
                               poster_path = i.poster_path
                               saveTopRate(tittle,r_date,poster_path)
                           }
                       }
                        val Gmgr = GridLayoutManager(activity!!, 3)
                        adapter =
                            TopAdapter(
                                activity!!,
                                list
                            )
                        rv_toprate.adapter = adapter
                        rv_toprate.layoutManager = Gmgr
                    }
                }

            })

    }
    private fun saveTopRate(t:String,r:String,p:String){
        class SaveTopRate:AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg p0: Void?): Void? {
                val movies = TopRateEntity()
                movies.tittle = t
                movies.release_date = r
                movies.poster_path = p
                DemoClient.getDemoInstance(activity!!).demoDb.demoDao().demoInsertTopRate(movies)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity!!,"Successfully Saved",Toast.LENGTH_SHORT).show()
            }



        }
        val str = SaveTopRate()
        str.execute()
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
