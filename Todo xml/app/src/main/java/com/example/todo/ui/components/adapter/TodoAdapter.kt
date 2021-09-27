package com.example.todo.ui.components.adapter

import trikita.anvil.RenderableAdapter

class ListAdapter<T : Any>(private val renderable: (T) -> Unit) : RenderableAdapter() {

    var items: List<T> = mutableListOf()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.let { it::class.java.hashCode() } ?: 0
    }

    override fun getItem(position: Int): T? {
        return items.getOrNull(position)
    }


    override fun view(position: Int) {
        getItem(position)?.let { renderable.invoke(it) }
    }

}