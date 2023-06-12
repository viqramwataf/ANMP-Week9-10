package com.example.todoapp.util
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDB(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        "newtododb"
    ).addMigrations(MIGRATION_1_2).build()

    return db
}

val MIGRATION_1_2 = object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table todo add column priority integer default 3 not null")
    }
}