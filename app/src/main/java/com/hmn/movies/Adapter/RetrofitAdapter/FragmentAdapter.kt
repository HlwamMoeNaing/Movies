package com.hmn.movies.Adapter.RetrofitAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hmn.movies.ui.RetrofitFrag.NowShowingFragment
import com.hmn.movies.ui.RetrofitFrag.PopularFragment
import com.hmn.movies.ui.RetrofitFrag.TopRateFragment
import com.hmn.movies.ui.RetrofitFrag.UpcomingFragment

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0){
            PopularFragment()
        }else if( position == 1){
            NowShowingFragment()
        }else (if (position == 2){
            TopRateFragment()
        }else{
            UpcomingFragment()
        }) as Fragment
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Popular Movies"
            1 -> "Now Showing"
            2 -> "Top Rated"
            3->"Upcoming"
            else->null
        }
    }

}