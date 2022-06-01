package com.example.schedule.scheduleParser

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.schedule.dataBase.GroupsViewModel
import com.example.schedule.dataBase.ScheduleGroups
import com.example.schedule.fragments.RegistrationFragment
import org.json.JSONArray
import java.io.Serializable


class Parser (private val registrationFragment: RegistrationFragment) :Serializable {
    private lateinit var mGroupsViewModel: GroupsViewModel
    private lateinit var groupsConverter: GroupsConverter
    private val link =
        "https://script.google.com/macros/s/AKfycby94mnfA_ZCxu3_5Vw7wKWc0sgwNltlP8Tnhvd0uQISERowzevts1Mm3viuRUz55KfG/exec?weekDay="
    private var daysArray = arrayOfNulls<String>(8)
    fun downloadJson(context: Context){
        val queue = Volley.newRequestQueue(context)
        mGroupsViewModel = ViewModelProvider(registrationFragment).get(GroupsViewModel::class.java)
        groupsConverter = GroupsConverter()
        for (j in 1..7) {
            val request = StringRequest(Request.Method.GET, link + j, { response ->
                val jArray = JSONArray(response)
                for (i in 0..jArray.length() - 1) {
                    daysArray[j] = jArray.toString()
                    //mGroupsViewModel.deleteAll()
                    mGroupsViewModel.addGroups(ScheduleGroups(j, jArray.toString()))
                }
            }, {
            })

            queue.add(request)
        }
    }
}