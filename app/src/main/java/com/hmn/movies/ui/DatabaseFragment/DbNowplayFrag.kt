package com.hmn.movies.ui.DatabaseFragment


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmn.movies.Adapter.RoomAdapter.RnowPlayAdapter

import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.NowPlay.NowPlayClient
import com.hmn.movies.RoomDatabase.NowPlay.NowPlayEntity
import kotlinx.android.synthetic.main.fragment_db_nowplay.*

/**
 * A simple [Fragment] subclass.
 */
class DbNowplayFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_db_nowplay, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getNowPlay()

    }

    private fun getNowPlay(){
        class GetNowPlay:AsyncTask<Void,Void,List<NowPlayEntity>>(){
            override fun doInBackground(vararg p0: Void?): List<NowPlayEntity> {
                return NowPlayClient.getNInstance(activity!!).nowplayDatabase.nowplayDao().nowplay
            }

            override fun onPostExecute(result: List<NowPlayEntity>?) {
                super.onPostExecute(result)
                val adp =RnowPlayAdapter(activity!!,result!!)
                val GMgr = GridLayoutManager(activity!!,3)
                rv_db_nowplay.layoutManager = GMgr
                rv_db_nowplay.adapter = adp
            }

        }
        val gnp = GetNowPlay()
        gnp.execute()
    }



}
