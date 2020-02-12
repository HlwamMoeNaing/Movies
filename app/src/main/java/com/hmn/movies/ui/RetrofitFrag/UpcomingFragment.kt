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
import com.hmn.movies.Adapter.RetrofitAdapter.UpcomingAdapter
import com.hmn.movies.Model.Upcoming
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Database.DemoClient

import com.hmn.movies.RoomDatabase.Entity.UpcomingEntity


import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment(),SearchView.OnQueryTextListener {

    lateinit var adapter:UpcomingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getUpcoming()
        val search = activity!!.findViewById<SearchView>(R.id.upcoming_search)
        search.setOnQueryTextListener(this)
    }
    private fun getUpcoming(){
        RetrofitHelper.getRetrofit<Retro_service>().upCom()
            .enqueue(object : Callback<Upcoming> {
                override fun onFailure(call: Call<Upcoming>, t: Throwable) {
                    Log.e("@up","fail")
                }

                override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                    var tittle:String
                    var r_date:String
                    var poster_path:String
                    if (response.isSuccessful){
                        Log.e("@up","Success")
                        val data = response.body()!!
                        val list = data.results
                        fb_upcoming_dowm.setOnClickListener {
                            for (i in list){
                                tittle = i.title
                                r_date = i.release_date
                                poster_path = i.poster_path
                                saveUpcoming(tittle,r_date,poster_path)
                            }
                        }
                        val Gmgr = GridLayoutManager(activity!!,3)
                         adapter =
                            UpcomingAdapter(
                                activity!!,
                                list
                            )
                        rv_upcoming.adapter = adapter
                        rv_upcoming.layoutManager = Gmgr
                    }
                }

            })
    }

    private fun saveUpcoming(t:String,r:String,p:String){
        class SaveUpcoming:AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg p0: Void?): Void? {
                val movies = UpcomingEntity()
                movies.tittle = t
                movies.release_date = r
                movies.poster_path =p
                DemoClient.getDemoInstance(activity!!).demoDb.demoDao().demoInsertUpcoming(movies)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity!!,"Successfully Saved",Toast.LENGTH_SHORT).show()
            }

        }
        val sc = SaveUpcoming()
        sc.execute()
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
