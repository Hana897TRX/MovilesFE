package com.hana897trx.finalexam.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.R
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notes : List<NoteModel> = ArrayList<NoteModel>()

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtTitle : TextView
        private var txtPriority : TextView
        private var txtDescription : TextView

        init {
            txtTitle = itemView.findViewById(R.id.txtTitle)
            txtPriority = itemView.findViewById(R.id.txtPriority)
            txtDescription = itemView.findViewById(R.id.txtDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes : List<NoteModel>){
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int) : NoteModel {
        return notes[position]
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        var currentNode = notes[position]
        holder.itemView.txtTitle.text = currentNode.title
        holder.itemView.txtDescription.text = currentNode.description
        holder.itemView.txtPriority.text = currentNode.priority.toString()
    }

}