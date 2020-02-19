package com.coroutinedemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coroutinedemo.R
import com.coroutinedemo.navigation.TransitionManager

class TodoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TransitionManager.replaceFragment(
            this,
            TodoListFragment(),
            R.id.fl_container_todo,
            false,
            TransitionManager.TransitionAnimation.TRANSITION_NONE
        )
    }
}
