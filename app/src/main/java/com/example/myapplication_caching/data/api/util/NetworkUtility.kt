package com.example.myapplication_caching.data.api.util

import android.util.Log
import com.example.myapplication_caching.data.models.FilmTrailNetworkModel
import com.example.myapplication_caching.data.models.FilmsNetworkModel
import org.json.JSONArray
import org.json.JSONObject

object NetworkUtility {

    private val TAG = "NetworkUtility"
    fun generateListFromTdmbApi(data: JSONObject): ArrayList<FilmsNetworkModel> {

        val result: JSONArray = data.get("results") as JSONArray
        val x = ArrayList<FilmsNetworkModel>()
        for (t in 0 until result.length()) {
            val dt = result.get(t) as JSONObject
                val id = dt.get("id").toString().toInt()
                val name = dt.get("title").toString()
                val populate = dt.get("vote_count").toString().toInt()

                x.add(FilmsNetworkModel(name, id, populate))


            }
            return x
        }




    fun generateListFromMiniFilmsApi(data: JSONObject):ArrayList<FilmsNetworkModel>{
        val result: JSONArray = data.get("results") as JSONArray
        val x = ArrayList<FilmsNetworkModel>()
        for (t in 0 until result.length()) {
            val dt = result.get(t) as JSONObject
                val id = dt.get("imdb_id").toString().replace("tt","").toInt()
                val name = dt.get("title").toString()
                val populate = dt.get("popularity").toString().toInt()

                x.add(FilmsNetworkModel(name, id, populate))
            }
        return x
        }

    fun generateFilmsTrailFromApi(data: JSONObject):FilmTrailNetworkModel {

            val result: JSONArray = data.get("results") as JSONArray
            if (result.isNull(0)){
                Log.i(TAG, "generateFilmsTrailFromApi: error $result")
               return FilmTrailNetworkModel(ERROR)
            }
            val dt = result.get(0) as JSONObject
            val key = dt.get("key").toString()


            return FilmTrailNetworkModel(key = key)

    }

    const val ERROR = "error"

}



