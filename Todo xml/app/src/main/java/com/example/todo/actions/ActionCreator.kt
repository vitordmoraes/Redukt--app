package com.example.todo.actions

import com.github.raulccabreu.redukt.actions.Action
import com.example.todo.App
import com.example.todo.actions.Actions.ADD_TODO
import com.example.todo.actions.Actions.DELETE_DONE
import com.example.todo.actions.Actions.TODO_TOGGLE
import com.example.todo.model.Todo


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






}