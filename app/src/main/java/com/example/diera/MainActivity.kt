package com.example.diera

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diera.databinding.ActivityMainBinding
import com.example.diera.viewmodel.ToDoViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ToDoAdapter(viewModel)

        viewModel.items.observe(this) { items ->
            (binding.recyclerView.adapter as ToDoAdapter).submitList(items)
        }

        binding.addButton.setOnClickListener {
            showAddItemDialog()
        }
    }

    private fun showAddItemDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New ToDo")
        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Add") { dialog, _ ->
            val task = input.text.toString()
            if (task.isNotBlank()) {
                viewModel.addItem(task)
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}
