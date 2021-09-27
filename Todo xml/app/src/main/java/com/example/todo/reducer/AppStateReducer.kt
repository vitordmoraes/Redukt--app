package com.example.todo.reducer

import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer
import com.example.todo.actions.Actions
import com.example.todo.model.AppState
import com.example.todo.model.Todo

class AppStateReducer : BaseAnnotatedReducer<AppState>() {

    @Reduce(Actions.ADD_TODO)
    fun addTodo(state: AppState, newItem: Todo): AppState {
        state.todos.let { return state.copy(todos = state.todos.plus(newItem)) }
    }

    @Reduce(Actions.DELETE_DONE)
    fun deleteDone(state: AppState, newList: List<Todo>): AppState {
        state.todos.let {
            return state.copy(todos = state.todos.filter { !it.completed }.map { it.copy() })
        }
    }

    @Reduce(Actions.TODO_TOGGLE)
    fun todoToggle(state: AppState, item: Int): AppState {
        state.todos.let {
            return state.copy(todos = state.todos.mapIndexed { index, todo ->
                if (index == item) {
                    todo.copy(completed = !todo.completed)
                } else todo
            })
        }
    }

}



