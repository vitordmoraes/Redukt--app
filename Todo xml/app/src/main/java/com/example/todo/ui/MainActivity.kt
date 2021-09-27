package com.example.todo.ui


import android.os.Bundle
import android.widget.Toast
import com.example.todo.R
import com.example.todo.actions.ActionCreator
import com.example.todo.components.adapter.TodoListAdapter
import com.example.todo.db.ObjectBox.store
import com.example.todo.model.AppState
import com.example.todo.model.Todo
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppLifecycle() {


    private val adapter by lazy { TodoListAdapter() }
    private val box :Box<Todo> by lazy { store.boxFor(Todo::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }


    private fun initUi() {
        recyclerView.adapter = adapter

        btnDeleteDone.setOnClickListener {
                ActionCreator.instance.deleteDone(state.todos)

        }


        btnAddTodo.setOnClickListener {
            val edtText = etTodo.text.toString()
            if (edtText.isEmpty()) {
                Toast.makeText(this, "Cannot store empty TODOS", Toast.LENGTH_SHORT).show()
            } else {
                val todo = Todo(0,edtText, false)
                ActionCreator.instance.addTodo(todo)
                etTodo.text.clear()
            }
        }
    }


    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        if (newState.todos != oldState.todos) return true
        return false
    }

    override fun onChanged(state: AppState) {
            adapter.submitList(state.todos)

    }





    }




