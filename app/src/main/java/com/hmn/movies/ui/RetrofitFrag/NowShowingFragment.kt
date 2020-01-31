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
import com.hmn.movies.Adapter.RetrofitAdapter.NowPlayAdapter
import com.hmn.movies.Model.NowPlay
import com.hmn.movies.Network.Retro_service
import com.hmn.movies.Network.RetrofitHelper.Companion.getRetrofit
import com.hmn.movies.R
import com.hmn.movies.RoomDatabase.NowPlay.NowPlayClient
import com.hmn.movies.RoomDatabase.NowPlay.NowPlayEntity
import kotlinx.android.synthetic.main.fragment_now_showing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class NowShowingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_showing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNowPlay()
    }

    private fun getNowPlay() {
        getRetrofit<Retro_service>().getNowPlay()
            .enqueue(object : Callback<NowPlay> {
                override fun onFailure(call: Call<NowPlay>, t: Throwable) {
                    Toast.makeText(activity, "Failed to Response", Toast.LENGTH_LONG).show()
                    Log.e("@np", "Fail")
                }

                override fun onResponse(call: Call<NowPlay>, response: Response<NowPlay>) {
                    if (response.isSuccessful) {
                        Log.e("@np", "success")
                        val data = response.body()!!
                        val j = data.results
                        for (i in j) {
                            val tit = i.title
                            val r_date = i.release_date
                            val p_path = i.poster_path
                           saveNowPlay(tit,r_date,p_path)
                        }
                        val list = data.results
                        val Gmgr = GridLayoutManager(activity!!, 3)
                        val adapter =
                            NowPlayAdapter(
                                activity!!,
                                list
                            )
                        rv_nowplay.layoutManager = Gmgr
                        rv_nowplay.adapter = adapter
                    }
                }

            })
    }

private fun saveNowPlay(t:String,r:String,p:String){
    class SaveNowPlay:AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg p0: Void?): Void? {
            val nMovie = NowPlayEntity()
            nMovie.tittle = t
            nMovie.release_date = r
            nMovie.poster_path = p
            NowPlayClient.getNInstance(activity!!).nowplayDatabase.nowplayDao().insertNplay(nMovie)
            return null

        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Toast.makeText(activity, "Saved to NowPlay", Toast.LENGTH_LONG).show()
        }

    }
    val snp = SaveNowPlay()
    snp.execute()
}

}
