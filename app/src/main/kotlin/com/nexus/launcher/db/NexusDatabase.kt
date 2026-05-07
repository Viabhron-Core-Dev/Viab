package com.nexus.launcher.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * NexusDatabase: SQLite storage for workspace layout and sidebar state.
 * Replaces JSON-based persistence for performance on low-end hardware.
 */
class NexusDatabase(context: Context) : SQLiteOpenHelper(context, "nexus.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE items (id TEXT PRIMARY KEY, label TEXT, package TEXT, class TEXT, type TEXT, container TEXT, x INTEGER, y INTEGER)")
        db.execSQL("CREATE TABLE sidebar (id TEXT, position INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}
