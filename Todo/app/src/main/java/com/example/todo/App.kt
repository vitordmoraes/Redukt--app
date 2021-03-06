package com.example.todo

import android.app.Application
import android.content.Context
import com.example.todo.db.ObjectBox
import com.github.raulccabreu.redukt.Redukt
import com.example.todo.model.AppState
import com.example.todo.reducer.AppStateReducer

class App : Application() {

    companion object {
        lateinit var redukt: Redukt<AppState>

        fun initializeRedukt(context: Context, appState: AppState): Redukt<AppState> {
            val redukt = Redukt(appState)

            addReducers(redukt)
//            addMiddlewares(context, redukt)

            return redukt
        }

        private fun addReducers(redukt: Redukt<AppState>){
            redukt.reducers["appStateReducers"] = AppStateReducer()
        }

//        private fun addMiddlewares(context: Context, redukt: Redukt<AppState>){
//            redukt.middlewares["todoListMiddlewares"] = TodoListMiddleware()
//        }
    }

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
        initializeRedukt(applicationContext,
            AppState()
        ).let {
            redukt = it
        }
    }

    override fun onTerminate() {
        redukt.stop()
        super.onTerminate()
    }
}