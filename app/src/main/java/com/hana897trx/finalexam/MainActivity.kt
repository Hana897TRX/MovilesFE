package com.hana897trx.finalexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.Repository.NoteRepository
import com.hana897trx.finalexam.ViewModel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var noteView : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteView = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteView.getAllNodes().observe(this,  Observer<List<NoteModel>> {
            // Update RecyclerView
            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT)
                    .show()
        })
    }
}
