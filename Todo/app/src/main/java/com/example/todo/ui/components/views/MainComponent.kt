package com.example.todo.ui.components.views

import android.content.Context
import android.widget.LinearLayout
import com.example.todo.App
import com.example.todo.actions.ActionCreator
import com.example.todo.model.AppState
import com.example.todo.model.Todo
import com.example.todo.ui.components.adapter.ListAdapter
import trikita.anvil.Anvil
import trikita.anvil.Anvil.render
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView

inline fun mainComponent(crossinline func: MainComponent.() -> Unit){
    highOrderComponent(func)
}

class MainComponent(c: Context) : RenderableView(c) {

    private var todoText: String = ""

    private val state: AppState
    get() = App.redukt.state

    private val todoAdapter =
        ListAdapter<Todo> { todo ->
        todoComponent{
            getTodo(todo)
        }
    }

    override fun view() {
        state.todos.let {todoAdapter.items = it}
        linearLayout {
            size(FILL, WRAP)
            orientation(LinearLayout.VERTICAL)

            linearLayout {
                size(FILL, WRAP)

                editText {
                    size(0, WRAP)
                    weight(1f)
                    text(todoText)
                    onTextChanged { s ->
                        todoText = s.toString()
                        Anvil.render()
                    }
                }

                button {
                    size(WRAP, WRAP)
                    layoutGravity(CENTER_VERTICAL)
                    text("Add")
                    enabled(todoText.trim().isNotEmpty())
                    onClick {
                        ActionCreator.instance.addTodo(todoText)
                        todoText = ""
                    }
                }
            }

            button {
                size(BaseDSL.FILL, WRAP)
                padding(dip(5))
                text("Clear checked tasks")
                onClick {
                    ActionCreator.instance.deleteDone(state.todos)
                }
            }


            listView {
                size(FILL, WRAP)
                itemsCanFocus(true)
                onItemClick { _, _, pos, _ ->
                    ActionCreator.instance.todoToggle(pos)
                }
                adapter(todoAdapter)
            }
        }
    }


}