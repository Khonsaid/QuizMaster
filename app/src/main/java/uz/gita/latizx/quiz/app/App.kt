package uz.gita.latizx.quiz.app

import android.app.Application
import android.content.Context
import android.view.Window
import uz.gita.latizx.quiz.data.LocalStorage

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this.getSharedPreferences("quiz_pref", Context.MODE_PRIVATE))
    }
}