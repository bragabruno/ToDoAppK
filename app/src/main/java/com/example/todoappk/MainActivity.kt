package com.example.todoappk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoappk.databinding.ActivityMainBinding
import com.example.todoappk.model.TaskPresentation

class MainActivity : AppCompatActivity(), CreateTodoFragment.CreateTodo {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        context
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        inflateCreateTodoFragment()

//        binding.etTodoInput

//        binding.etTodoInput // NotInitializedException
    }

    override fun createNewTodo(todo: TaskPresentation) {
        supportFragmentManager.findFragmentById(R.id.display_fragment)?.let {
            (it as DisplayFragment).updateAdapter(todo)
        }
    }

    /**
     * Create a dynamic fragmetn using
     * support fragment manager
     */
    private fun inflateCreateTodoFragment() {

    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.add_fragment, CreateTodoFragment())
    transaction.commit()
    }
}
