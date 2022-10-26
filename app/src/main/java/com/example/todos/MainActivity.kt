package com.example.todos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotesRVAdapter.INotesRVAdapter {
    private lateinit var viewModel: NoteViewModel
    private lateinit var rvTodo:RecyclerView
    private lateinit var input:EditText
    private lateinit var Buttton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        rvTodo.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        rvTodo.adapter=adapter

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, Observer {List->
            List?.let {
                adapter.updateList(it)
            }
        })

    }

    private fun initViews() {
        rvTodo=findViewById(R.id.rvtodo)
        input=findViewById(R.id.input)
        Buttton=findViewById(R.id.button)
    }

    override fun onItemClicked(note: Note) {
    viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()

    }

    fun submitData(view: View) {
        val noteText=input.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_LONG).show()

        }
    }
}