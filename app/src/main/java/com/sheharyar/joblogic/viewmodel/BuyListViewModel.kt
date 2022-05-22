package com.sheharyar.joblogic.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sheharyar.joblogic.data.repository.JobLogicRepository

class BuyListViewModel@ViewModelInject constructor(
    private
    val repository: JobLogicRepository
) : ViewModel() {
    val buyListData = repository.getAllItems()
    val getItemsFromDb = repository.getAllItemsFromDB()
}