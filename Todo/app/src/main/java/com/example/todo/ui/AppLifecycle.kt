package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import com.github.raulccabreu.redukt.states.StateListener
import com.example.todo.App
import com.example.todo.model.AppState

abstract class AppLifecycle : AppCompatActivity(), StateListener<AppState> {

    protected val state: AppState
        get() = App.redukt.state

    override fun onStart() {
        super.onStart()
        App.redukt.listeners.add(this)
        onChanged(state)
    }

    override fun onStop() {
        super.onStop()
        App.redukt.listeners.remove(this)
    }
}