//package com.vitordmoraes.tryingredux.components.views
//
//import android.content.Context
//import android.widget.LinearLayout
//import com.example.todo.App
//import com.example.todo.actions.ActionCreator
//import com.example.todo.model.AppState
//import trikita.anvil.BaseDSL.FILL
//import trikita.anvil.BaseDSL.size
//import trikita.anvil.DSL.*
//import trikita.anvil.RenderableAdapter
//import trikita.anvil.RenderableView
//
//class TodoList(c: Context) : RenderableView(c) {
//
//    var message = ""
//
//    private val state: AppState
//        get() = App.redukt.state
//
//
//
//    val todoAdapter = RenderableAdapter.withItems(state.todos) {pos, value ->
//        linearLayout{
//            size(FILL, WRAP)
//            minHeight(dip(72))
//
//            textView {
//                size(0, WRAP)
//                weight(1f)
//                layoutGravity(CENTER_VERTICAL)
//                padding(dip(5))
//                text(value.text)
//            }
//
//            checkBox{
//                size(WRAP, WRAP)
//                margin(dip(5))
//                layoutGravity(CENTER_VERTICAL)
//                focusable(false)
//                focusableInTouchMode(false)
//                clickable(false)
//                checked(value.completed)
//            }
//        }
//    }
//
//
//
//
//    override fun view() {
//        todoAdapter.notifyDataSetChanged()
//
//        linearLayout {
//            size(FILL, WRAP)
//            orientation(LinearLayout.VERTICAL)
//
//            linearLayout {
//                size(FILL, WRAP)
//
//                editText {
//                    size(0, WRAP)
//                    weight(1f)
//                    text(message)
//                    onTextChanged { s ->
//                        message = s.toString()
//                    }
//                }
//
//                button {
//                    size(WRAP, WRAP)
//                    layoutGravity(CENTER_VERTICAL)
//                    text("Add")
//                    onClick {
//                        ActionCreator.instance.addTodo(message)
//                        message = ""
//                    }
//                }
//            }
//
//            button {
//                size(FILL, WRAP)
//                padding(dip(5))
//                text("Clear checked tasks")
//                onClick {
//                    ActionCreator.instance.deleteDone(state.todos)
//                }
//            }
//
//            listView {
//                size(FILL, WRAP)
//                itemsCanFocus(true)
//                onItemClick { parent, view, pos, id ->
//                    ActionCreator.instance.todoToggle(pos)
//                }
//                adapter(todoAdapter)
//            }
//        }
//    }
//}