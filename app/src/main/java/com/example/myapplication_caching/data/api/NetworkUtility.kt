package com.example.myapplication_caching.data.api

import com.example.myapplication_caching.data.models.FilmsNetworkModel
import org.json.JSONArray
import org.json.JSONObject

object NetworkUtility {

    fun generateListFromTdmbApi(data: JSONObject, flag: String): ArrayList<FilmsNetworkModel> {

        val result: JSONArray = data.get("results") as JSONArray
        val x = ArrayList<FilmsNetworkModel>()
        for (t in 0 until result.length()) {
            val dt = result.get(t) as JSONObject
            if (flag == TDMB_FLAG) {
                val id = dt.get("id").toString().toInt()
                val name = dt.get("title").toString()
                val populate = dt.get("vote_count").toString().toInt()

                x.add(FilmsNetworkModel(name, id, populate))
            } else if (flag == MINI_FILM_FLAG) {
                val id = dt.get("imdb_id").toString().replace("tt", "").toInt()
                val name = dt.get("title").toString()
                val populate = dt.get("popularity").toString().toInt()
                x.add(FilmsNetworkModel(name, id, populate))

            }

        }

        return x
    }

    const val TDMB_FLAG = "tdmb"
    const val MINI_FILM_FLAG = "mini"


}



