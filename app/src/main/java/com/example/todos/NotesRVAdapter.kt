package com.example.todos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class NotesRVAdapter(private val context: Context,private val listener:INotesRVAdapter) :RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val allNotes=ArrayList<Note>()

     class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_item,parent, false))
       viewHolder.deleteButton.setOnClickListener{
           listener.onItemClicked(allNotes[viewHolder.adapterPosition])

       }
           return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    val currentNote=allNotes[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newsList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newsList)

        notifyDataSetChanged()
    }
    interface INotesRVAdapter{
        fun onItemClicked(note: Note)
    }
}