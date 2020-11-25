package com.hana897trx.finalexam.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hana897trx.finalexam.Models.NoteModel

@Dao
interface NoteDao {

    @Insert
    fun Insert(note : NoteModel)

    @Update
    fun Update(note : NoteModel)

    @Delete
    fun Delete(note : NoteModel)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes() : LiveData<List<NoteModel>>

}