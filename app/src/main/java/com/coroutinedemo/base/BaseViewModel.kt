package com.coroutinedemo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.coroutinedemo.component.SingleEvent
import com.coroutinedemo.component.SingleLiveEvent
import com.coroutinedemo.entity.Entity

open class BaseViewModel : ViewModel() {

    /**
     * Live data to handle error
     */
    private var errorLiveData = MediatorLiveData<SingleEvent<Entity.ErrorEntity>>()


    val mErrorLiveData: LiveData<SingleEvent<Entity.ErrorEntity>>
        get() = errorLiveData

    /**
     * Live data to handle loading
     */
    private var loadingLiveData = SingleLiveEvent<Boolean>()

    val mLoadingLiveData: SingleLiveEvent<Boolean>
        get() = loadingLiveData

    /**
     * Method call to handle loading
     */
    fun showLoading(show: Boolean) {
        loadingLiveData.postValue(show)
    }

    protected fun handleError(error: Entity.ErrorEntity) {
        errorLiveData.postValue(SingleEvent(error))
    }
}