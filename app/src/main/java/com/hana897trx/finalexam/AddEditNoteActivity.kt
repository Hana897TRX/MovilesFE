package com.hana897trx.finalexam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.note_item.*

class AddEditNoteActivity : AppCompatActivity() {

    companion object {
        var EXTRA_ID = "com.hana897trx.finalexam.EXTRA_ID"
        var EXTRA_TITLE = "com.hana897trx.finalexam.EXTRA_TITLE"
        var EXTRA_DESCRIPTION = "com.hana897trx.finalexam.EXTRA_DESCRIPTION"
        var EXTRA_PRIORITY = "com.hana897trx.finalexam.EXTRA_PRIORITY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        number_priority.maxValue = 10
        number_priority.minValue = 1

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val intent = intent

        if(intent.hasExtra(EXTRA_ID)) {
            title = "Edit note"
            txtTitleR.setText(intent.getStringExtra(EXTRA_TITLE))
            txtDescriptionR.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            number_priority.value = intent.getIntExtra(EXTRA_PRIORITY, -1)
        }
        else
            title = "Add Note"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_note -> {
                saveNote();
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote(){
        if(txtTitleR.text.isEmpty() || txtDescriptionR.text.isEmpty()){
            Toast.makeText(this@AddEditNoteActivity, "No se han introducido datos", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, txtTitleR.text.toString())
        data.putExtra(EXTRA_DESCRIPTION, txtDescriptionR.text.toString())
        data.putExtra(EXTRA_PRIORITY, number_priority.value.toString())

        val id = intent.getIntExtra(EXTRA_ID, -1)
        if(id != -1)
            data.putExtra(EXTRA_ID, id)

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}