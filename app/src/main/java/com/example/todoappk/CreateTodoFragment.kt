package com.example.todoappk

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.todoappk.databinding.CreateTodoBinding
import com.example.todoappk.model.TaskPresentation

class CreateTodoFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: CreateTodoBinding // create_todo.xml + Binding
    private val adapter: ArrayAdapter<String> by lazy {
        createAdapter() // for casting use operator 'as'
    }

    private var selectedCategory: String? = null

    interface CreateTodo {
        fun createNewTodo(todo: TaskPresentation)
    }

    private lateinit var listener: CreateTodo

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context){ // is like instanceOf in Java
            is CreateTodo -> listener = context
            else -> throw Exception("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        binding = CreateTodoBinding.inflate(
            inflater,
            container,
            false
        )

        binding.ibSaveTask.setOnClickListener(this)
        binding.spCategory.adapter = adapter
        binding.spCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedCategory = adapter.getItem(position)

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

        // View view = inflater,.inflate(R.layout.create, container, false);
        // view.findViewById(R.id.).setOn
        // return view
        return binding.root
    }

    private fun createAdapter(): ArrayAdapter<String> {
        return ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.categories)
        )
    }

    override fun onClick(v: View?) {
        binding.tilTodoTask.editText?.let { todoTask ->
            selectedCategory?.let { category ->
                if (todoTask.text.isNotEmpty() && selectedCategory != null)
                    updateDisplayFragment(todoTask.text.toString(), category)
            }
        } ?: Toast.makeText(context, "No empty values", Toast.LENGTH_LONG)
            .show() // nullables ?: right

    }

    private fun updateDisplayFragment(todoTask: String, selectedCategory: String) {
        listener.createNewTodo(
            TaskPresentation(todoTask, selectedCategory)
        )
    }
}

