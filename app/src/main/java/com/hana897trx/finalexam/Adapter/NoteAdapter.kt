package com.hana897trx.finalexam.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hana897trx.finalexam.Models.NoteModel
import com.hana897trx.finalexam.R
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter() : ListAdapter<NoteModel, NoteAdapter.NoteHolder>(TaskDiffCallback()) {

    private var notes : List<NoteModel> = ArrayList<NoteModel>()
    private lateinit var listener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(note : NoteModel)
    }

    fun setOnItemClickListener(_listener: OnItemClickListener) {
        listener = _listener
    }

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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    fun setNotes(notes : List<NoteModel>){
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int) : NoteModel {
        return getItem(position)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        var currentNode = getItem(position)
        holder.itemView.txtTitle.text = currentNode.title
        holder.itemView.txtDescription.text = currentNode.description
        holder.itemView.txtPriority.text = currentNode.priority.toString()

        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(getItem(position))
        }
    }
}