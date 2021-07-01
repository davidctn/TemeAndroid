package com.example.secondhomework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.secondhomework.R
import com.example.secondhomework.VolleyConfigSingleton
import com.example.secondhomework.adapters.PhotoAdapter
import com.example.secondhomework.models.Album
import com.example.secondhomework.models.Photo
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val CHOOSEN_POS="1"
private const val URL="https://jsonplaceholder.typicode.com/photos"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var choosenPos:String?=null

    private val photoList :ArrayList<Photo> = ArrayList()

    private val photoAdapter = PhotoAdapter(photoList)

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
        val view  = inflater.inflate(R.layout.fragment_third,container,false)
        setupRecyclerView(view)
        getPosts("albumId=1")
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,choosenPos:String) =
                ThirdFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                        putString(CHOOSEN_POS,choosenPos)
                    }
                }
    }

    fun setupRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.photo_rv);

        val gridLayoutManager = GridLayoutManager(view.context,2)

        photoList.clear()


        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = photoAdapter
    }

    fun getPosts(userId : String){
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.context)
        val queue = volleyConfigSingleton.requestQueue

        val url = URL+ "?" + "albumId=" + choosenPos

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
                val photoUrl = postJson.getString("url")
                val thumbnailUrl = postJson.getString("thumbnailUrl")

                val photo : Photo = Photo(title,photoUrl,thumbnailUrl)
                photoList.add(photo)
            }
        }
        photoAdapter.notifyDataSetChanged()
    }
}