package com.hana897trx.finalexam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hana897trx.finalexam.Adapter.NoteAdapter
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.ViewModel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var noteView: NoteViewModel

    companion object {
        const val ADD_NOTE_CODE = 202
        const val EDIT_NOTE_CODE = 204
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        val noteAdapter: NoteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter

        // ViewModel
        noteView = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteView.getAllNodes().observe(this, Observer<List<NoteModel>> {
            noteAdapter.submitList(it)
            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT)
                .show()
        })

        // Delete Note
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteView.delete(noteAdapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT)
                    .show()
            }

        }).attachToRecyclerView(recyclerView)

        // ItemClickListener
        noteAdapter.setOnItemClickListener(object: NoteAdapter.OnItemClickListener{
            override fun onItemClick(note: NoteModel) {
                val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.id)
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.title)
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.description)
                intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.priority)
                startActivityForResult(intent, EDIT_NOTE_CODE)
            }

        })
    }

    fun addNote(view: View) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        startActivityForResult(intent, ADD_NOTE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_CODE && resultCode == Activity.RESULT_OK) {
            val note = NoteModel(
                0,
                data!!.getStringExtra(AddEditNoteActivity.EXTRA_TITLE)!!,
                data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION)!!,
                data.getStringExtra(AddEditNoteActivity.EXTRA_PRIORITY).toString().toInt()
            )
            noteView.insert(note)
            Toast.makeText(this@MainActivity, "Note saved", Toast.LENGTH_SHORT)
                .show()
        }
        else if (requestCode == EDIT_NOTE_CODE && resultCode == Activity.RESULT_OK){
            if(data!!.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1) == -1){
                Toast.makeText(this@MainActivity, "Note cannot be updated", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            val note = NoteModel(
                data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1),
                data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE)!!,
                data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION)!!,
                data.getStringExtra(AddEditNoteActivity.EXTRA_PRIORITY).toString().toInt()
            )
            noteView.update(note)
            Toast.makeText(this@MainActivity, "Note updated", Toast.LENGTH_SHORT)
                .show()
        }
        else {
            Toast.makeText(this@MainActivity, "Note not saved", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAllNodes -> {
                noteView.deleteAllNodes()
                Toast.makeText(this@MainActivity, "All notes deleted", Toast.LENGTH_SHORT)
                    .show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
