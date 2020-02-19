package com.coroutinedemo.repository

import com.coroutinedemo.base.BaseRepositoryImpl
import com.coroutinedemo.model.ErrorResponse
import com.coroutinedemo.model.Todo
import com.coroutinedemo.network.DataServiceFactory
import com.coroutinedemo.network.ResultState
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 * Created by shivanggoel on 19,February,2020
 */
class TodoRepositoryImpl : BaseRepositoryImpl(), TodoRepository {

    override suspend fun getTodoList(): ResultState<MutableList<Todo>> {
        return try {
//            delay(7000)
            ResultState.Success(DataServiceFactory.todoListService.getTodoList())
        }catch (e:Exception){
            ResultState.Error(e, ErrorResponse(0, ""))
        }
    }
}