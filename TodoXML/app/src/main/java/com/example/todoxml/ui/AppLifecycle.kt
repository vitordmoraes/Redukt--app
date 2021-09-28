package com.example.todoxml.ui

import androidx.appcompat.app.AppCompatActivity
import com.github.raulccabreu.redukt.states.StateListener
import com.example.todoxml.App
import com.example.todoxml.model.AppState

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