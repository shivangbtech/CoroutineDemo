package com.coroutinedemo.extentions

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun Any.toast(context: Context?, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}

fun EditText.resetText() = setText("")