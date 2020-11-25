package com.hana897trx.finalexam

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hana897trx.finalexam.Adapter.NoteAdapter
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.Repository.NoteRepository
import com.hana897trx.finalexam.ViewModel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var noteView : NoteViewModel
    companion object {
        const val ADD_NOTE_CODE = 202
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        val noteAdapter : NoteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter

        // ViewModel
        noteView = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteView.getAllNodes().observe(this,  Observer<List<NoteModel>> {
            noteAdapter.setNotes(it)
            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT)
                    .show()
        })

        // Delete Note
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback( 0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteView.delete(noteAdapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView)
    }

    fun addNote(view : View){
        val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
        startActivityForResult(intent, ADD_NOTE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADD_NOTE_CODE && resultCode == Activity.RESULT_OK){
            val note = NoteModel(0,
                data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE)!!,
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)!!,
                data.getStringExtra(AddNoteActivity.EXTRA_PRIORITY).toString().toInt()
            )
            noteView.insert(note)
            Toast.makeText(this@MainActivity, "Note saved", Toast.LENGTH_SHORT)
                .show()
        }
        else{
            Toast.makeText(this@MainActivity, "Note not saved", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
