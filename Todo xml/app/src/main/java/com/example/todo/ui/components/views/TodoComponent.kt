package com.example.todo.ui.components.views

import android.content.Context
import com.example.todo.model.Todo
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView


inline fun todoComponent(crossinline func: TodoComponent.() -> Unit) {
    highOrderComponent(func)
}

class TodoComponent(c: Context) : RenderableView(c) {

    private lateinit var todo: Todo

    override fun view() {
        linearLayout{
            size(FILL, WRAP)
            minHeight(dip(72))

            textView {
                size(0, WRAP)
                weight(1f)
                layoutGravity(CENTER_VERTICAL)
                padding(dip(5))
                text(todo.text)
            }

            checkBox {
                size(WRAP, WRAP)
                margin(dip(5))
                layoutGravity(CENTER_VERTICAL)
                focusable(false)
                focusableInTouchMode(false)
                clickable(false)
                checked(todo.completed)
            }
        }

    }

    fun getTodo(currTodo: Todo) {
        todo = currTodo
    }

}