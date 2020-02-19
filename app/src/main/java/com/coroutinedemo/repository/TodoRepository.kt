package com.coroutinedemo.repository

import com.coroutinedemo.base.BaseRepository
import com.coroutinedemo.model.Todo

/**
 * Created by shivanggoel on 19,February,2020
 */
interface TodoRepository: BaseRepository {

    suspend fun getTodoList():List<Todo>
}