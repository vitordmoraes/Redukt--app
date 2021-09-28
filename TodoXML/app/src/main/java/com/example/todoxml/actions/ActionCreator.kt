package com.example.todoxml.actions

import com.github.raulccabreu.redukt.actions.Action
import com.example.todoxml.App
import com.example.todoxml.actions.Actions.ADD_TODO
import com.example.todoxml.actions.Actions.DELETE_DONE
import com.example.todoxml.actions.Actions.GET_LIST
import com.example.todoxml.actions.Actions.TODO_TOGGLE
import com.example.todoxml.model.Todo


class ActionCreator private constructor(){

    private object Holder {
        val INSTANCE = ActionCreator()
    }

    companion object {
        val instance: ActionCreator by lazy { Holder.INSTANCE }
    }

    private fun asyncDispatch(action: Action<*>) {
        App.redukt.dispatch(action, true)
    }

    fun addTodo(todo: Todo) {
        asyncDispatch(Action(ADD_TODO,todo))
    }

    fun deleteDone(list: List<Todo>) {
        asyncDispatch(Action(DELETE_DONE,list))
    }

    fun todoToggle(index: Int) {
        asyncDispatch(Action(TODO_TOGGLE,index))
    }

    fun getList(list: List<Todo>) {
        asyncDispatch(Action(GET_LIST, list))
    }





}