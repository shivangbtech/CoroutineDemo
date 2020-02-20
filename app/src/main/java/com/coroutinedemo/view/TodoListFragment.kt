package com.coroutinedemo.view

import androidx.lifecycle.Observer
import com.coroutinedemo.BR
import com.coroutinedemo.R
import com.coroutinedemo.base.BaseFragment
import com.coroutinedemo.databinding.FragmentTodoListBinding
import com.coroutinedemo.extentions.observe
import com.coroutinedemo.navigation.NavigationContract
import com.coroutinedemo.viewmodel.TodoListViewModel

/**
 * Created by shivanggoel
 */
class TodoListFragment : BaseFragment<FragmentTodoListBinding, TodoListViewModel>() {

    override fun provideViewModelClass(): Class<TodoListViewModel> {
        return TodoListViewModel::class.java
    }

    override fun layoutId(): Int = R.layout.fragment_todo_list

    override val bindingVariable: Int
        get() = BR.viewmodel

    override val navigationContract: NavigationContract?
        get() = object : NavigationContract {

            override fun subscribeNetworkResponse() {
                super.subscribeNetworkResponse()
            }

            override fun subscribeNavigationEvent() {
                super.subscribeNavigationEvent()
                viewModel.reloadClick.observe(this@TodoListFragment, Observer {
                    viewModel.showError.set(false)
                    viewModel.showTodoList()
                })
            }
        }
}