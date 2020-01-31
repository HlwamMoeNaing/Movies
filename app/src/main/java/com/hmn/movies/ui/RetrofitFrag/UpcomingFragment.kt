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
import com.hmn.movies.Adapter.RetrofitAdapter.UpcomingAdapter
import com.hmn.movies.Model.Upcoming
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Upcoming.UpcomingClient
import com.hmn.movies.RoomDatabase.Upcoming.UpcomingEntity

import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUpcoming()
    }
    private fun getUpcoming(){
        RetrofitHelper.getRetrofit<Retro_service>().upCom()
            .enqueue(object : Callback<Upcoming> {
                override fun onFailure(call: Call<Upcoming>, t: Throwable) {
                    Log.e("@up","fail")
                }

                override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                    if (response.isSuccessful){
                        Log.e("@up","Success")
                        val data = response.body()!!
                        val list = data.results
                        for (i in list){
                            val ti = i.title
                            val rd= i.release_date
                            val po = i.poster_path
                            saveUpcoming(ti,rd,po)
                        }
                        val Gmgr = GridLayoutManager(activity!!,3)
                        val adp =
                            UpcomingAdapter(
                                activity!!,
                                list
                            )
                        rv_upcoming.adapter = adp
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
                movies.poster_path = p
                UpcomingClient.getUpInstance(activity!!).upcomingDatabase.upcomingDao().saveUpvoming(movies)

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity, "Saved to Upcoming", Toast.LENGTH_LONG).show()
            }

        }
        val sp = SaveUpcoming()
        sp.execute()
    }


}
