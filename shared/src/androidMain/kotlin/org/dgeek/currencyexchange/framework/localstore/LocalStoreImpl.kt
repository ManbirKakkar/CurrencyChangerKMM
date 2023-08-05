package org.dgeek.currencyexchange.framework.localstore

import android.content.Context
import android.content.SharedPreferences
import org.dgeek.currencyexchange.interactor.LocalStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class LocalStoreImpl actual constructor() : KoinComponent,
    LocalStore {
    private val context: Context by inject()
    private val localStore: SharedPreferences by lazy { getLocalStoreInstance() }
    private val localStoreEditor: SharedPreferences.Editor by lazy { getEditableLocalStoreInstance() }

    private fun getLocalStoreInstance(): SharedPreferences {
        return context.getSharedPreferences("currency", Context.MODE_PRIVATE)
    }


    private fun getEditableLocalStoreInstance(): SharedPreferences.Editor {
        return localStore.edit()
    }

    override fun <T> putValue(key: String, value: T) {
        when (value) {
            is String -> localStoreEditor.putString(key, value).commit()
            is Int -> localStoreEditor.putInt(key, value).commit()
            is Float -> localStoreEditor.putFloat(key, value).commit()
            is Double -> localStoreEditor.putString(key, value.toString()).commit()
            is Long -> localStoreEditor.putLong(key, value).commit()
            is Boolean -> localStoreEditor.putBoolean(key, value).commit()
            is Set<*> -> localStoreEditor.putStringSet(key, value as Set<String>).commit()
        }
    }

    override fun <T> getValue(key: String, default: T): T {
        return when (default) {
            is String -> localStore.getString(key, default) as T
            is Int -> localStore.getInt(key, default) as T
            is Float -> localStore.getFloat(key, default) as T
            is Double -> localStore.getString(key, default.toString())?.toDouble() as T
            is Long -> localStore.getLong(key, default) as T
            is Boolean -> localStore.getBoolean(key, default) as T
            is Set<*> -> localStore.getStringSet(key, default as Set<String>) as T
            else -> "" as T
        }
    }

    override fun clear() {
        localStoreEditor.clear().commit()
    }
}