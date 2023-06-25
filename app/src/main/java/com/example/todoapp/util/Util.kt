package com.example.todoapp.util
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context):TodoDatabase{
    return Room.databaseBuilder(context,TodoDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2, MIGRATION_2_3).build()
}

val MIGRATION_1_2 = object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table todo add column priority integer default 3 not null")
    }
}

val MIGRATION_2_3 = object:Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table todo add column is_done integer default 0 not null"); // Room secara otomatis mengubah true menjadi 1 dan false menjadi 0 serta sebaliknya karena SQLite tidak mendukung boolean.
    }
}