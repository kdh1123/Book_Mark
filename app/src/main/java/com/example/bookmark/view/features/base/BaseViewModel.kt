package com.example.bookmark.view.features.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmark.view.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

open class BaseViewModel : ViewModel(){
    protected val isLoading: MediatorLiveData<Boolean> = MediatorLiveData()
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    val tokenErrorEvent = MutableLiveData<String>()

    fun combineLoadingVariable(vararg lives: MutableLiveData<Boolean>) {
        lives.forEach { liveData ->
            isLoading.addSource(liveData) { isLoading.value = lives.any { it.value == true } }
        }
    }

    fun <T> Flow<Resource<T>>.divideResult(
        isLoading: MutableLiveData<Boolean>,
        successAction: (T?) -> Unit,
        errorAction: (String?) -> Unit
    ) = onEach { resource ->
        when (resource) {
            is Resource.Success -> {
                isLoading.value = false
                successAction.invoke(resource.data)
            }
            is Resource.Loading -> {
                isLoading.value = true
            }
            is Resource.Error -> {
                isLoading.value = false
                    errorAction.invoke(resource.message)
            }
        }
    }

}