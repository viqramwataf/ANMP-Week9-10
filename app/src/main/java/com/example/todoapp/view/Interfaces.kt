package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.model.Todo

interface TodoItemLayoutInterface {
    fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj:Todo)

    fun onTodoEditClick(v: View)
}

interface TodoCreateLayoutInterface {
    fun onRadioClick(v: View, priority: Int, obj: Todo)

    fun onButtonAddClick(v:View)
}