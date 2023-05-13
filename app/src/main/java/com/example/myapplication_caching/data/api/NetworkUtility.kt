package com.example.myapplication_caching.data.api

import com.example.myapplication_caching.data.models.FilmTrailNetworkModel
import com.example.myapplication_caching.data.models.FilmsNetworkModel
import org.json.JSONArray
import org.json.JSONObject

object NetworkUtility {

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

    fun generateFilmsTrailFromApi(data: JSONObject):FilmTrailNetworkModel{
        val result: JSONArray = data.get("results") as JSONArray
        val dt = result.get(0) as JSONObject
        val key = dt.get("key").toString()

        return FilmTrailNetworkModel(key = key)
    }


}



