package com.example.schedule.dataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupsViewModel(application: Application) :AndroidViewModel(application) {
    val readAllData: LiveData<List<ScheduleGroups>>
    private val repository: GroupsRepository

    init {
        val scheduleDao = ScheduleGroupDataBase.getDatabase(application).scheduleDao()
        repository = GroupsRepository(scheduleDao)
        readAllData = repository.readAllData
    }
    fun addGroups(scheduleGroups: ScheduleGroups) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.addGroups(scheduleGroups)
        }
    }
    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}