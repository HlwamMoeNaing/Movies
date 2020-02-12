package com.hmn.movies.ui.DatabaseFragment


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hmn.movies.Adapter.RoomAdapter.RoomAdapter

import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.Database.DemoClient

import com.hmn.movies.RoomDatabase.Entity.MoviesEntity
import kotlinx.android.synthetic.main.fragment_db_popular.*

/**
 * A simple [Fragment] subclass.
 */
class DbPopularFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_db_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getMoview()
    }


private fun getMoview(){
class GetMovies:AsyncTask<Void,Void,List<MoviesEntity>>(){
    override fun doInBackground(vararg p0: Void?): List<MoviesEntity> {
        return DemoClient.getDemoInstance(activity!!).demoDb.demoDao().demoMovies
    }

    override fun onPostExecute(result: List<MoviesEntity>) {
        super.onPostExecute(result)
       val l = result.map { it.tittle }.toString()
        Log.e("@gm",l)
       // val list = arrayListOf<MoviesEntity>()
        val adp =
            RoomAdapter(activity!!, result)
        val Gmgr = GridLayoutManager(activity!!,3)
        rv_db_populer.adapter = adp
        rv_db_populer.layoutManager = Gmgr

    }

}
    val gm = GetMovies()
    gm.execute()
}

}
