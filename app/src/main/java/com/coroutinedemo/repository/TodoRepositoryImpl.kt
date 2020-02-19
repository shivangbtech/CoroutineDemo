package com.coroutinedemo.repository

import com.coroutinedemo.base.BaseRepositoryImpl
import com.coroutinedemo.model.Todo
import com.coroutinedemo.network.DataServiceFactory

/**
 * Created by shivanggoel on 19,February,2020
 */
class TodoRepositoryImpl() : BaseRepositoryImpl(), TodoRepository {

    override suspend fun getTodoList(): List<Todo> {
        return DataServiceFactory.todoListService.getTodos()
    }
}