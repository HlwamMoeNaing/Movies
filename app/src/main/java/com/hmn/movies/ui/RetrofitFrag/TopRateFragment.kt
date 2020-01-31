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
import com.hmn.movies.Adapter.RetrofitAdapter.TopAdapter
import com.hmn.movies.Model.Result3
import com.hmn.movies.Model.Result4
import com.hmn.movies.Model.TopRate
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.NowPlay.NowPlayEntity
import com.hmn.movies.RoomDatabase.TopRate.TopRateClient
import com.hmn.movies.RoomDatabase.TopRate.TopRateEntity

import kotlinx.android.synthetic.main.fragment_top_rate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class TopRateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTopRete()
    }

    private fun getTopRete() {
        getRetrofit<Retro_service>().getTopRate()
            .enqueue(object : Callback<TopRate> {
                override fun onFailure(call: Call<TopRate>, t: Throwable) {
                    Toast.makeText(activity, "Failed to Response", Toast.LENGTH_LONG).show()
                    Log.e("@tr", "Fail")
                }

                override fun onResponse(call: Call<TopRate>, response: Response<TopRate>) {
                    if (response.isSuccessful) {
                        Log.e("@tr", "success")
                        val data = response.body()!!
                        val list = data.results
                        for (i in list) {
                            val ti = i.title
                            val rd = i.release_date
                            val po = i.poster_path
                            saveTopRate(ti,rd,po)

                        }



                        val Gmgr = GridLayoutManager(activity!!, 3)
                        val adaptert =
                            TopAdapter(
                                activity!!,
                                list
                            )
                        rv_toprate.adapter = adaptert
                        rv_toprate.layoutManager = Gmgr
                    }
                }

            })

    }

    private fun saveTopRate(t:String,r:String,p:String){
        class SaveTopRate:AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg p0: Void?): Void? {
                val tMovie = TopRateEntity()
                tMovie.tittle =t
                tMovie.release_date = r
                tMovie.poster_path = p

                TopRateClient.getTInstance(activity!!).toprateDatabase.topRateDao().saveTopRate(tMovie)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity, "Saved to TopRate", Toast.LENGTH_LONG).show()
            }

        }
        val str = SaveTopRate()
        str.execute()
    }






}
