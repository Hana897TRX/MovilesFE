package com.hana897trx.finalexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.Repository.NoteRepository
import com.hana897trx.finalexam.ViewModel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var noteView : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteView = ViewModelProvider(this).get(NoteViewModel::class.java)
//        noteView.getAllNodes().observe(this, Observer<List<NoteModel>>{
//            onContentChanged(List<NoteModel> notes)
//        })
        //noteView.getAllNodes().observe(this, onChanged)
    }
}
