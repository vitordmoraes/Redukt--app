//package com.example.todo.middleWares
//
//import com.example.todo.actions.Actions
//import com.example.todo.model.AppState
//import com.example.todo.model.Todo
//import com.github.raulccabreu.redukt.actions.Action
//import com.github.raulccabreu.redukt.middlewares.BaseAnnotatedMiddleware
//import com.github.raulccabreu.redukt.middlewares.BeforeAction
//import io.objectbox.Box
//
//class TodoListMiddleware : BaseAnnotatedMiddleware<AppState>() {
//
//    private lateinit var box: Box<Todo>
//
//    @BeforeAction(Actions.ADD_TODO)
//    fun addToBox(state: AppState, action: Action<*>){
//        box.put(state.todos)
//    }
//}