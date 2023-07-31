package com.sonder.userlist.ui.list

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.sonder.userlist.core.NetworkModule.getRetrofit
import com.sonder.userlist.data.network.ApiService
import com.sonder.userlist.data.network.model.Result
import com.sonder.userlist.domain.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase): ViewModel() {
    val itemList: MutableLiveData<List<Result>> = MutableLiveData()
    val progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData()
    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    fun users(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){
            progressBarVisibility.postValue(true)
            val myResponse= getUsersUseCase.invoke()
            if (myResponse.isSuccessful){
                val response = myResponse.body()
                if (response != null) {
                    val items = response.results
                    itemList.postValue(items)
                    progressBarVisibility.postValue(false)
                }
            }else{
                progressBarVisibility.postValue(false)
            }
        }
    }
}