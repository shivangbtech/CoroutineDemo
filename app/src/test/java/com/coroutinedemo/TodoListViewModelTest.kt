package com.coroutinedemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coroutinedemo.network.DataServiceFactory
import com.coroutinedemo.network.ITodoApi
import com.coroutinedemo.utils.MockResponse.createMockResponse
import com.coroutinedemo.viewmodel.TodoListViewModel
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.net.HttpURLConnection

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
class TodoListViewModelTest : BaseUnitTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var iTodoApi: ITodoApi
    lateinit var todoListViewModel: TodoListViewModel

    @Before
    override fun setUp(){
        super.setUp()
        iTodoApi = DataServiceFactory.todoListService
        todoListViewModel = TodoListViewModel()
    }

    @Test
    fun testGetOtpSuccess(){
        Assert.assertNotNull(todoListViewModel)
        Assert.assertNotNull(iTodoApi)
        mockWebServer.enqueue(createMockResponse("todo_list_success",  HttpURLConnection.HTTP_OK))
        todoListViewModel.showTodoList()
        Assert.assertFalse(todoListViewModel.showError.get())
    }

}
