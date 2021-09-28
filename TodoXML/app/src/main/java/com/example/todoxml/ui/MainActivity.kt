package com.example.todoxml.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import com.example.todoxml.R
import com.example.todoxml.actions.ActionCreator
import com.example.todoxml.db.ObjectBox.store
import com.example.todoxml.ui.adapter.TodoListAdapter
//import com.vitordmoraes.tryingredux.components.views.TodoList
import com.example.todoxml.model.AppState
import com.example.todoxml.model.Todo
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppLifecycle() {

    private val box : Box<Todo> by lazy { store.boxFor(Todo::class.java) }
    private val adapter by lazy { TodoListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    private fun initUi() {
        recyclerView.adapter = adapter

        btnDeleteDone.setOnClickListener {
            ActionCreator.instance.deleteDone(state.todos)
        }


        btnAddTodo.setOnClickListener {
            val edtText = etTodo.text.toString()
            val todo = Todo(0,edtText,completed = false)
            if (edtText.isEmpty()) {
                Toast.makeText(this, "Cannot store empty TODOS", Toast.LENGTH_SHORT).show()
            } else {
                ActionCreator.instance.addTodo(todo)
                etTodo.text.clear()
                box.put(todo)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_visibility_menu, menu)
        return true
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        if (newState.todos != oldState.todos) return true
        return false
    }

    override fun onChanged(state: AppState) {
        adapter.submitList(state.todos)
        }
    }



