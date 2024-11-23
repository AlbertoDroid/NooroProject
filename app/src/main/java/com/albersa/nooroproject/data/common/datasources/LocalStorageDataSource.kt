package com.albersa.nooroproject.data.common.datasources

interface LocalStorageDataSource {
    fun saveString(key:String, value: String)
    fun saveInt(key:String, value: Int)
    fun saveBoolean(key:String, value: Boolean)
    fun getString(key: String): String?
    fun getInt(key: String): Int
    fun getBoolean(key: String): Boolean
}