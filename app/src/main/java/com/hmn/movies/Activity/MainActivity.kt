package com.hmn.movies.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.hmn.movies.Adapter.RetrofitAdapter.FragmentAdapter
import com.hmn.movies.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
//    lateinit var dbPopularFrag: DbPopularFrag
//    lateinit var dbNowplayFrag: DbNowplayFrag
//    lateinit var dbUpcomingFrag: DbUpcomingFrag
//    lateinit var dbTopRateFrag: DbTopRateFrag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val simpleFragmenAdapter =
            FragmentAdapter(
                supportFragmentManager
            )

        view_pager.adapter = simpleFragmenAdapter

        tabs.setupWithViewPager(view_pager)





        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Mingalar Movies"
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout, toolbar, (R.string.open), (R.string.close)
        ) {

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this@MainActivity)

//        dbPopularFrag = DbPopularFrag()
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_layout, dbPopularFrag)
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.popular -> {
                startActivity(Intent(this,RoomActivity::class.java))
//                dbPopularFrag = DbPopularFrag()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frag_view, dbPopularFrag)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
            }
            R.id.now_showing -> {
                startActivity(Intent(this,RoomActivity::class.java))
//                dbNowplayFrag = DbNowplayFrag()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frag_view, dbNowplayFrag)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
            }
            R.id.top_rated -> {
                startActivity(Intent(this,RoomActivity::class.java))
//                dbTopRateFrag = DbTopRateFrag()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frag_view, dbTopRateFrag)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
            }
            R.id.upcoming -> {
                startActivity(Intent(this,RoomActivity::class.java))
//                dbUpcomingFrag = DbUpcomingFrag()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frag_view, dbUpcomingFrag)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
            }


        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }


    }
}






