package com.example.undiaku.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveMinMaxModel(model: MinMaxModel) {
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_MIN_NUMBER, model.minNumber)
        editor.putInt(KEY_MAX_NUMBER, model.maxNumber)
        editor.putInt(KEY_RESULT_NUMBER, model.resultNumber)
        editor.apply()
    }

    fun getMinMaxModel(): MinMaxModel {
        val minNumber = sharedPreferences.getInt(KEY_MIN_NUMBER, 0)
        val maxNumber = sharedPreferences.getInt(KEY_MAX_NUMBER, 0)
        val resultNumber = sharedPreferences.getInt(KEY_RESULT_NUMBER, 0)
        return MinMaxModel(minNumber, maxNumber, resultNumber)
    }

    fun saveNameList(names: List<ListNameModel>) {
        val editor = sharedPreferences.edit()
        val nameSet = HashSet<String>(names.map { it.name })
        editor.putStringSet(KEY_NAME_LIST, nameSet)
        editor.apply()
    }

    fun getNameList(): List<ListNameModel> {
        val nameSet = sharedPreferences.getStringSet(KEY_NAME_LIST, HashSet<String>())
        return nameSet?.map { ListNameModel(it) } ?: emptyList()
    }

    companion object {
        private const val PREFS_NAME = "PreferencesManager"
        private const val KEY_MIN_NUMBER = "minNumber"
        private const val KEY_MAX_NUMBER = "maxNumber"
        private const val KEY_RESULT_NUMBER = "resultNumber"
        private const val KEY_NAME_LIST = "nameList"
    }
}