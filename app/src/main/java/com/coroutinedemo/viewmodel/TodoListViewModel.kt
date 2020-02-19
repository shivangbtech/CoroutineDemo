package com.coroutinedemo.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.coroutinedemo.BR
import com.coroutinedemo.R
import com.coroutinedemo.base.BaseViewModel
import com.coroutinedemo.model.Todo
import com.coroutinedemo.repository.TodoRepository
import com.coroutinedemo.repository.TodoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding
import timber.log.Timber


/**
 * Created by shivanggoel
 */
class TodoListViewModel : BaseViewModel() {

    val todoItems: ObservableList<Todo> = ObservableArrayList()

    val todoItemBinding: ItemBinding<Todo> = ItemBinding.of(BR.item, R.layout.list_item_todo)

    private val todoRepository: TodoRepository = TodoRepositoryImpl()

    init {
        showTodoList()
    }

    fun showTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var list = todoRepository.getTodoList()
                withContext(Dispatchers.Main) {
                    todoItems.addAll(list)
                }
                Timber.d("size of data..." + list.size)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }
}