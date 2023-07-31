package com.sonder.userlist.ui.detail

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonder.userlist.data.network.model.Result
import com.sonder.userlist.domain.GetUserByGender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getUserByGender: GetUserByGender): ViewModel() {
    val itemDetail: MutableLiveData<List<Result>> = MutableLiveData()
    val progressBarVisibility: MutableLiveData<Int> = MutableLiveData(View.VISIBLE)
    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    fun getUserByGender(gender: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            progressBarVisibility.postValue(View.VISIBLE)
            val myResponse = getUserByGender.invoke(gender)
            if (myResponse.isSuccessful) {
                val response = myResponse.body()
                Log.i("Carlos", response.toString())
                if (response != null) {
                    itemDetail.postValue(response.results)
                    progressBarVisibility.postValue(View.GONE)
                }
            } else {
                progressBarVisibility.postValue(View.GONE)
            }
        }
    }
}