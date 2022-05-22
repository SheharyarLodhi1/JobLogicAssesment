package com.sheharyar.joblogic.data.remote

import javax.inject.Inject

class NyRemoteDataSource @Inject constructor(
    private val nyService: NyService
): BaseDataSource() {

    suspend fun getAllItemsList() = getResult { nyService.getAllItems() }
    suspend fun getAllDummyDataList() = getResult { nyService.getAllCallList() }
}