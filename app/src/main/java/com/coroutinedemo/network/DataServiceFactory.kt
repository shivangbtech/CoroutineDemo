package com.coroutinedemo.network

/**
 * Created by shivanggoel on 19,February,2020
 */
object DataServiceFactory {

    val todoListService: ITodoApi = NetworkClient.getNetworkClient().create(ITodoApi::class.java)

}