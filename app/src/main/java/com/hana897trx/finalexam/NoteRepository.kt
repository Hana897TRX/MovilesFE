package com.hana897trx.finalexam

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.hana897trx.finalexam.DB.NoteDB
import com.hana897trx.finalexam.Dao.NoteDao
import com.hana897trx.finalexam.Models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(context: Context) : AppCompatActivity() {
    private lateinit var noteDao : NoteDao
    private lateinit var allNotes : LiveData<List<NoteModel>>

    init {
        var noteDB = NoteDB.getInstance(context)
        noteDao = noteDB.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note : NoteModel){
        lifecycleScope.launch(Dispatchers.IO) {
            noteDao.Insert(note)
        }
    }

    fun update(note : NoteModel){
        lifecycleScope.launch(Dispatchers.IO) {
            noteDao.Update(note)
        }
    }

    fun delete(note: NoteModel){
        lifecycleScope.launch(Dispatchers.IO) {
            noteDao.Delete(note)
        }
    }

    fun deleteAllNodes(){
        lifecycleScope.launch(Dispatchers.IO){
            noteDao.deleteAllNotes()
        }
    }

    fun getAllNotes() : LiveData<List<NoteModel>> {
        return allNotes
    }
}