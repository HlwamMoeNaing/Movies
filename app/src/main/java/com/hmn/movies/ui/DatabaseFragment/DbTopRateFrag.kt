package com.hmn.movies.ui.DatabaseFragment


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hmn.movies.Adapter.RoomAdapter.TopRateAdapter

import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.TopRate.TopRateClient
import com.hmn.movies.RoomDatabase.TopRate.TopRateEntity
import kotlinx.android.synthetic.main.fragment_db_top_rate.*

/**
 * A simple [Fragment] subclass.
 */
class DbTopRateFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_db_top_rate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getTopRate()

    }

    private fun getTopRate(){
        class GetTopRate:AsyncTask<Void,Void,List<TopRateEntity>>(){
            override fun doInBackground(vararg p0: Void?): List<TopRateEntity> {
                return TopRateClient.getTInstance(activity!!).toprateDatabase.topRateDao().topRate
            }

            override fun onPostExecute(result: List<TopRateEntity>?) {
                super.onPostExecute(result)
                val adp = TopRateAdapter(activity!!,result!!)
                val Gmgr = GridLayoutManager(activity!!,3)
                rv_dv_toprate.adapter = adp
                rv_dv_toprate.layoutManager = Gmgr
            }

        }
        val gtr = GetTopRate()
        gtr.execute()
    }



}
