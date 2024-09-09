package com.example.diera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diera.data.ToDoItem
import com.example.diera.viewmodel.ToDoViewModel

class ToDoAdapter(private val viewModel: ToDoViewModel) :
    ListAdapter<ToDoItem, ToDoAdapter.ToDoViewHolder>(ToDoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel)
    }

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)
        private val doneCheckBox: CheckBox = itemView.findViewById(R.id.doneCheckBox)

        fun bind(item: ToDoItem, viewModel: ToDoViewModel) {
            taskTextView.text = item.task
            doneCheckBox.isChecked = item.isDone
            doneCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != item.isDone) {
                    viewModel.toggleItemDone(item.id)
                }
            }
        }
    }

    class ToDoDiffCallback : DiffUtil.ItemCallback<ToDoItem>() {
        override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem == newItem
        }
    }
}
