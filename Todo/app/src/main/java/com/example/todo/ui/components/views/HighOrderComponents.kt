package com.example.todo.ui.components.views

import android.view.View
import trikita.anvil.Anvil
import trikita.anvil.DSL

inline fun <reified T : View> highOrderComponent(crossinline func: T.() -> Unit) {
    DSL.v(T::class.java) {
        val block : T = Anvil.currentView()
        block.func()
    }
}