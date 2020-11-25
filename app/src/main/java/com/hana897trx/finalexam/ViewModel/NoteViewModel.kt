package com.hana897trx.finalexam.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.Repository.NoteRepository

class NoteViewModel(context: Context) : AndroidViewModel(context as Application) {
    private lateinit var nodeRepository : NoteRepository
    private lateinit var allNotes : LiveData<List<NoteModel>>

    init {
        nodeRepository = NoteRepository(context)
        allNotes = nodeRepository.getAllNotes()
    }

    fun insert(node : NoteModel){
        nodeRepository.insert(node)
    }

    fun update(node : NoteModel){
        nodeRepository.update(node)
    }

    fun delete(node : NoteModel){
        nodeRepository.delete(node)
    }

    fun deleteAllNodes(){
        nodeRepository.deleteAllNodes()
    }

    fun getAllNodes() : LiveData<List<NoteModel>> {
        return allNotes
    }
}