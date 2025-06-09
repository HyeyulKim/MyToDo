package com.example.mytodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mytodo.data.local.entity.TodoEntity
//Room Database 선언
//DB 버전 관리(MIGRATION 추가)
@Database(
    entities = [TodoEntity::class],
    version = 2, // 기존 1 → 2로 올림
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todos ADD COLUMN dueDate TEXT")
            }
        }
    }
}
