package com.example.todoxml

import android.app.Application
import android.content.Context
import com.example.todoxml.db.ObjectBox
import com.example.todoxml.middlewares.ListMiddleware
import com.github.raulccabreu.redukt.Redukt
import com.example.todoxml.model.AppState
import com.example.todoxml.reducer.AppStateReducer

class App : Application() {

    companion object {
        lateinit var redukt: Redukt<AppState>

        fun initializeRedukt(context: Context, appState: AppState): Redukt<AppState> {
            val redukt = Redukt(appState)

            addReducers(redukt)
            addMiddlewares(context, redukt)

            return redukt
        }

        private fun addReducers(redukt: Redukt<AppState>){
            redukt.reducers["appStateReducers"] = AppStateReducer()
        }

        private fun addMiddlewares(context: Context, redukt: Redukt<AppState>) {
            redukt.middlewares["listMiddleware"] = ListMiddleware()
        }
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