package com.hmn.movies.ui.DatabaseFragment


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hmn.movies.Adapter.RoomAdapter.UpcomingAdapter

import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Upcoming.UpcomingClient
import com.hmn.movies.RoomDatabase.Upcoming.UpcomingEntity
import kotlinx.android.synthetic.main.fragment_db_upcoming.*

/**
 * A simple [Fragment] subclass.
 */
class DbUpcomingFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_db_upcoming, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getUpcoming()
    }


    private fun getUpcoming(){
        class GetUpcoming:AsyncTask<Void,Void,List<UpcomingEntity>>(){
            override fun doInBackground(vararg p0: Void?): List<UpcomingEntity> {
                return UpcomingClient.getUpInstance(activity!!).upcomingDatabase.upcomingDao().upcoming
            }

            override fun onPostExecute(result: List<UpcomingEntity>?) {
                super.onPostExecute(result)
                val adp = UpcomingAdapter(activity!!,result!!)
                val Gmgr = GridLayoutManager(activity!!,3)
                rv_db_upcoming.adapter = adp
                rv_db_upcoming.layoutManager = Gmgr
            }

        }
        val gp = GetUpcoming()
        gp.execute()
    }


}
