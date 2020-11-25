package com.hana897trx.finalexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hana897trx.finalexam.Adapter.NoteAdapter
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.Repository.NoteRepository
import com.hana897trx.finalexam.ViewModel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var noteView : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        var noteAdapter : NoteAdapter = NoteAdapter()

        recyclerView.adapter = noteAdapter

        noteView = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteView.getAllNodes().observe(this,  Observer<List<NoteModel>> {
            noteAdapter.setNotes(it)
            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT)
                    .show()
        })
    }
}
