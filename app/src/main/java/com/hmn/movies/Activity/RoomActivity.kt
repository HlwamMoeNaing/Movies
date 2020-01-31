package com.hmn.movies.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hmn.movies.R
import com.hmn.movies.ui.DatabaseFragment.DbNowplayFrag
import com.hmn.movies.ui.DatabaseFragment.DbPopularFrag
import com.hmn.movies.ui.DatabaseFragment.DbTopRateFrag
import com.hmn.movies.ui.DatabaseFragment.DbUpcomingFrag
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.app_bar_main.*


class RoomActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBar = supportActionBar
        actionBar?.title = "Room Database"
        bt_get_pupular.setOnClickListener {
            loadFragment(DbPopularFrag())
        }
        bt_get_nowplay.setOnClickListener {
            loadFragment(DbNowplayFrag())
        }
        bt_get_top_rate.setOnClickListener {
            loadFragment(DbTopRateFrag())
        }
        bt_get_upcoming.setOnClickListener {
            loadFragment(DbUpcomingFrag())
        }
    }
    private fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransation = fragmentManager.beginTransaction()
        fragmentTransation.replace(R.id.frag_view,fragment)
        fragmentTransation.commit()
    }
}
