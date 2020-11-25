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

class AddNoteActivity : AppCompatActivity() {
    companion object {
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
            Toast.makeText(this@AddNoteActivity, "No se han introducido datos", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, txtTitleR.text.toString())
        data.putExtra(EXTRA_DESCRIPTION, txtDescriptionR.text.toString())
        data.putExtra(EXTRA_PRIORITY, number_priority.value.toString())

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}