package com.example.todoxml.middlewares

import com.example.todoxml.actions.ActionCreator
import com.example.todoxml.actions.Actions
import com.example.todoxml.model.AppState
import com.example.todoxml.model.Todo
import com.github.raulccabreu.redukt.actions.Action
import com.github.raulccabreu.redukt.middlewares.BaseAnnotatedMiddleware
import com.github.raulccabreu.redukt.middlewares.BeforeAction
import io.objectbox.Box

class ListMiddleware : BaseAnnotatedMiddleware<AppState>() {

    private lateinit var box: Box<Todo>


    @BeforeAction(Actions.GET_LIST)
    fun getTodo(state: AppState, action: Action<*>){
        ActionCreator.instance.getList(box.all)
    }

    @BeforeAction(Actions.GET_LIST)
    fun deleteDone(state: AppState, action: Action<*>){
        ActionCreator.instance.deleteDone(box.all)
    }



}