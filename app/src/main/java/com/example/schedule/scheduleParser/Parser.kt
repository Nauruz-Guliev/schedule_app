package com.example.schedule.scheduleParser

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.io.Serializable


class Parser :Serializable{
    val link =
        "https://script.google.com/macros/s/AKfycby94mnfA_ZCxu3_5Vw7wKWc0sgwNltlP8Tnhvd0uQISERowzevts1Mm3viuRUz55KfG/exec?weekDay="
    var arrayListGroup = ArrayList<String>()
    var daysArray = arrayOfNulls<String>(8)

    fun downloadJson(context: Context){
        daysArray[0] = "asd"
        val queue = Volley.newRequestQueue(context)
        var responseBody = ""
        for (j in 1..7) {
            val request = StringRequest(Request.Method.GET, link + j, { response ->
                responseBody = response
                //Log.e("error", response.toString() + "asdasdasd")
                var jArray = JSONArray(response)
                for (i in 0..jArray.length() - 1) {
                    var jArr = JSONArray(jArray[i].toString())
                    arrayListGroup.add(jArr.toString())

                    Log.e("error", jArray[i].toString())
                    daysArray[j] = jArray.toString()
                }
            }, {
            })
            queue.add(request)
        }
        Log.e( "error",daysArray[0].toString() + "asdasd")
    }


}