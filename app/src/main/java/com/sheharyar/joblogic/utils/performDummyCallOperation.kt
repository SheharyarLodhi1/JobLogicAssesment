package com.sheharyar.joblogic.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performDummyCallOperation(networkCall: () -> Resource<T>) : LiveData<T> =

    liveData(Dispatchers.IO) {
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            //saveCallResult(responseStatus.data!!)
            //emitSource(responseStatus.data)

        }
}