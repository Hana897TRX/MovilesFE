package com.hana897trx.finalexam.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.hana897trx.finalexam.Models.NoteModel

class TaskDiffCallback : DiffUtil.ItemCallback<NoteModel>() {
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return  oldItem.title.equals(newItem.title) &&
                oldItem.description.equals(newItem.description) &&
                oldItem.priority.equals(newItem.priority)
    }
}