package com.sheharyar.joblogic.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sheharyar.joblogic.data.entities.DataList
import com.sheharyar.joblogic.data.repository.JobLogicRepository
import com.sheharyar.joblogic.utils.Resource
import kotlinx.coroutines.*

class CallListViewModel@ViewModelInject constructor(
    private
    val repository: JobLogicRepository
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    //val loginData = MutableLiveData<Resource<LoginModel>>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()
    val callListData = MutableLiveData<Resource<List<DataList>>>()


    fun getCallResponse() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCallListData()
            withContext(Dispatchers.Main) {
                if (response.status.equals(Resource.Status.SUCCESS)) {
                    val body = response
                    callListData.postValue(body)
                    loading.value = false
                } else {
                    onError("Error : ${response} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}