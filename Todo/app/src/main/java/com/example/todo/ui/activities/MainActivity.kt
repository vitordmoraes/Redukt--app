package com.example.todo.ui.activities


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.todo.db.ObjectBox.store
import com.example.todo.model.AppState
import com.example.todo.model.Todo
import com.example.todo.ui.components.views.mainComponent
import io.objectbox.Box
import trikita.anvil.RenderableView


class MainActivity : AppLifecycle() {


    private val box :Box<Todo> by lazy { store.boxFor(Todo::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView(this))
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        if (newState.todos != oldState.todos) return true
        return false
    }

    override fun onChanged(state: AppState) {
                Toast.makeText(this, "state changed", Toast.LENGTH_SHORT).show()
    }

    private fun getView(c: Context): View {
        return object : RenderableView(c) {
            override fun view() {
                mainComponent {}
            }
        }
    }



}




