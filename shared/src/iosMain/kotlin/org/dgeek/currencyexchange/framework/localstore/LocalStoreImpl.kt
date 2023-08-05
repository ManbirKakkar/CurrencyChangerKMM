package org.dgeek.currencyexchange.framework.localstore


import org.dgeek.currencyexchange.interactor.LocalStore
import platform.Foundation.NSBundle
import platform.Foundation.NSUserDefaults
import platform.darwin.NSInteger

actual class LocalStoreImpl actual constructor() :
    LocalStore {
    private val localStore: NSUserDefaults by lazy { getLocalStoreInstance() }
    private fun getLocalStoreInstance(): NSUserDefaults {
        return NSUserDefaults("currency")
    }

    override fun <T> putValue(key: String, value: T) {
        when (value) {
            is String -> {
                localStore.setObject(value, key)
            }
            is Long -> {
                localStore.setInteger(value as NSInteger, key)
            }
            is Int -> {
                localStore.setInteger(value.toLong(), key)
            }
            is Float -> {
                localStore.setFloat(value, key)
            }
            is Boolean -> {
                localStore.setBool(value, key)
            }
            is Double -> {
                localStore.setDouble(value, key)
            }
            else ->{
            }
        }
    }

    override fun <T> getValue(key: String, default: T): T {

        return when (default) {
            is String -> {
                (localStore.objectForKey(key) ?: default) as T
            }
            is Long ->{
                (localStore.integerForKey(key) ?: default) as T
            }
            is Int -> {
                (localStore.integerForKey(key).toInt() ?: default) as T
            }
            is Float -> {
                (localStore.floatForKey(key) ?: default) as T
            }
            is Boolean -> {
                (localStore.boolForKey(key) ?: default) as T
            }
            is Double -> {
                (localStore.doubleForKey(key) ?: default) as T
            }
            else -> {
                "" as T
            }
        }
    }

    override fun clear() {
        val domainName = NSBundle.mainBundle.bundleIdentifier
        localStore.removePersistentDomainForName(domainName?:"")
        localStore.synchronize()
    }
}