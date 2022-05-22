package com.sheharyar.joblogic.data.remote

import com.sheharyar.joblogic.data.entities.CallListModel
import com.sheharyar.joblogic.data.entities.DataList
import com.sheharyar.joblogic.data.entities.ItemToSell
import com.sheharyar.joblogic.data.entities.ListOfItems
import com.sheharyar.joblogic.utils.AppConstant
import retrofit2.Response
import retrofit2.http.GET

interface NyService {
    @GET(AppConstant.END_POINT_BUY_LIST)
    suspend fun getAllItems() : Response<List<ListOfItems>>

    @GET("imkhan334/demo-1/call")
    suspend fun getAllCallList() : Response<List<DataList>>
}