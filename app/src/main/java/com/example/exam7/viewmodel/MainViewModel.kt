package com.example.exam7.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam7.model.FootballMatch
import com.example.exam7.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _livedata = MutableLiveData<FootballMatch>()
    val livedata:LiveData<FootballMatch> = _livedata

    fun getMatch(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _livedata.postValue(RetrofitService.retrofitService.getMatch().body())
            }
        }
    }
}