package com.coroutinedemo.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.coroutinedemo.BR
import com.coroutinedemo.R
import com.coroutinedemo.base.BaseViewModel
import com.coroutinedemo.component.SingleLiveEvent
import com.coroutinedemo.model.Todo
import com.coroutinedemo.network.ResultState
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
    val showError = ObservableBoolean()
    private val todoRepository: TodoRepository = TodoRepositoryImpl()
    internal val reloadClick = SingleLiveEvent<Void>()
    val error = ObservableField<String>()
    init {
        showError.set(false)
        showTodoList()
    }

    fun showTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
               todoRepository.getTodoList().let {
                withContext(Dispatchers.Main){
                    when(it){
                        is ResultState.Success -> {
                            showError.set(false)
                            todoItems.addAll(it.data)
                        }
                        is ResultState.Error -> {
                            error.set(it.errorResponse.errorMessage)
                            showError.set(true)
//                            Timber.e(it.throwable)
                        }
                    }
                }
            }
        }
    }

    fun reloadClick(){
        reloadClick.call()
    }
}