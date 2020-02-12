package com.hmn.movies.Activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import com.google.android.material.navigation.NavigationView
import com.hmn.movies.Adapter.RetrofitAdapter.FragmentAdapter
import com.hmn.movies.Adapter.RetrofitAdapter.RecyclerAdapter
import com.hmn.movies.Model.MovieDetails
import com.hmn.movies.Model.Movies_data
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.R
import com.hmn.movies.ui.RetrofitFrag.PopularFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.popular -> {
                startActivity(Intent(this, RoomActivity::class.java))

            }
            R.id.now_showing -> {
                startActivity(Intent(this, RoomActivity::class.java))

            }
            R.id.top_rated -> {
                startActivity(Intent(this, RoomActivity::class.java))

            }
            R.id.upcoming -> {
                startActivity(Intent(this, RoomActivity::class.java))

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






