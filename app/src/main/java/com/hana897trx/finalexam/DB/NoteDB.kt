package com.hana897trx.finalexam.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hana897trx.finalexam.Dao.NoteDao
import com.hana897trx.finalexam.Models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDB : RoomDatabase(){
    abstract fun noteDao(): NoteDao
    companion object{
        private var INSTANCE: NoteDB? = null

        fun getInstance(context: Context) : NoteDB{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, NoteDB::class.java, "NoteDB").build()
            }
            return INSTANCE as NoteDB
        }
    }
}