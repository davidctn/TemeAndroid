package com.example.secondhomework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.secondhomework.R
import com.example.secondhomework.VolleyConfigSingleton
import com.example.secondhomework.adapters.AlbumAdapter
import com.example.secondhomework.adapters.PostAdapter
import com.example.secondhomework.models.Album
import com.example.secondhomework.models.Post
import com.example.secondhomework.models.User
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val CHOOSEN_POS="1"
private const val URL_PATH="https://jsonplaceholder.typicode.com/albums"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var choosenPos:String? = null
    val albumList :ArrayList<Album> = ArrayList<Album>()


    val albumAdapter : AlbumAdapter = AlbumAdapter(albumList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            choosenPos = it.getString(CHOOSEN_POS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        setupRecyclerView(view)
        getPosts("userId=1")
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,choosen:String) =
                SecondFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                        putString(CHOOSEN_POS,choosen)
                    }
                }
    }

    fun setupRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.album_rv);

        val linearLayoutManager = LinearLayoutManager(view.context);

        albumList.clear()


        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = albumAdapter
    }

    fun getPosts(userId : String){
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.context)
        val queue = volleyConfigSingleton.requestQueue

        val url = URL_PATH + "?" + "userId=" + choosenPos

        val getPostsRequest = StringRequest(
                Request.Method.GET,
                url,
                {response ->
                    handlePostResponse(response)
                },
                {error->
                    Toast.makeText(
                            activity,
                            "ERROR get posts failed with error: ${error.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
        )
        queue.add(getPostsRequest)
    }

    fun handlePostResponse(result:String){
        val postJsonArray = JSONArray(result)
        for (index in 0 until postJsonArray.length()){
            val postJson : JSONObject? = postJsonArray[index] as? JSONObject
            postJson?.let {
                val id = postJson.getString("id")
                val title = postJson.getString("title")

                val album :Album = Album(title,id)
                albumList.add(album)
            }
        }
        albumAdapter.notifyDataSetChanged()
    }
}