package com.sheharyar.joblogic.data.repository

import com.sheharyar.joblogic.data.local.JobLogicDao
import com.sheharyar.joblogic.data.remote.NyRemoteDataSource
import com.sheharyar.joblogic.utils.performDbOperation
import com.sheharyar.joblogic.utils.performDummyCallOperation
import com.sheharyar.joblogic.utils.performGetOperation
import javax.inject.Inject

class JobLogicRepository@Inject constructor(
    private val remoteDataSource: NyRemoteDataSource,
    private val localDataSource: JobLogicDao
) {
    fun getAllItems() = performGetOperation(
        databaseQuery = { localDataSource.getData() },
        networkCall = { remoteDataSource.getAllItemsList() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

   /* fun getAllCallList() = performDummyCallOperation(
        networkCall = { remoteDataSource.getAllDummyDataList() }
    )*/

    fun getAllItemsFromDB() = performDbOperation(
        databaseQuery = { localDataSource.getData() }
    )

    suspend fun getCallListData() =  remoteDataSource.getAllDummyDataList()


}