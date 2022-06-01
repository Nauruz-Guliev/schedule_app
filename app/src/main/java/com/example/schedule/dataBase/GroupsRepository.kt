package com.example.schedule.dataBase

import androidx.lifecycle.LiveData

class GroupsRepository (private val groupsDAO: GroupsDAO) {

    val readAllData: LiveData<List<ScheduleGroups>> = groupsDAO.readAllData()

    fun addGroups(scheduleGroups: ScheduleGroups) {
        groupsDAO.addGroups(scheduleGroups)
    }
    fun deleteAll(){
        groupsDAO.nukeTable()
    }
}