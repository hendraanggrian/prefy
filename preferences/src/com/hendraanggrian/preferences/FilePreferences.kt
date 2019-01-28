package com.hendraanggrian.preferences

import java.io.File
import java.util.Properties

internal class FilePreferences(private val file: File) : Preferences<FilePreferences.Editor> {

    private val properties = Properties()

    init {
        if (!file.exists()) {
            file.createNewFile()
        }
        file.inputStream().use { properties.load(it) }
    }

    override fun contains(key: String): Boolean = properties.containsKey(key)

    override fun getString(key: String): String = properties.getProperty(key)

    override fun getEditor(): Editor = Editor()

    inner class Editor : Preferences.Editor {

        override fun setString(key: String, value: String?) {
            properties.setProperty(key, value)
        }

        override fun setInt(key: String, value: Int) = setString(key, value.toString())

        override fun save() {
            file.outputStream().use {
                properties.save(it, null)
            }
        }
    }
}