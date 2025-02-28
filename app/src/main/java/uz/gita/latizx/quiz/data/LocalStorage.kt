package uz.gita.latizx.quiz.data

import android.content.SharedPreferences
import uz.gita.latizx.quiz.utils.CategoryEnum

class LocalStorage private constructor() {

    companion object {
        lateinit var instance: LocalStorage
            private set
        private lateinit var pref: SharedPreferences

        fun init(sharedPreferences: SharedPreferences) {
            if (!(::instance.isInitialized)) instance = LocalStorage()
            pref = sharedPreferences
        }
    }

    fun saveLevel(category: CategoryEnum, level: Int) {
        pref.edit().putInt("level$category", level).apply()
    }

    fun getLevel(category: CategoryEnum): Int = pref.getInt("level$category", 0)
}