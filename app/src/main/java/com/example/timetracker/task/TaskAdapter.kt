package com.example.timetracker.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetracker.R
import com.example.timetracker.space.Space
import org.joda.time.DateTime

class TaskAdapter constructor(private val dataSet: List<Task>, private val spaces: List<Space>?) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date = view.findViewById<TextView>(R.id.task_date)
        val time = view.findViewById<TextView>(R.id.total_task_time)
        val space = view.findViewById<TextView>(R.id.task_space_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_task, parent, false)
        return TaskAdapter.ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TaskAdapter.ViewHolder, position: Int) {
        val task = dataSet[position]
        viewHolder.date.text =
            DateTime(task.getStartTime()).toLocalDate().toString()
        viewHolder.time.text = task.getDuration()
        spaces?.let { it ->
            viewHolder.space.text = it.find { s -> s.getId() == task.getSpace() }?.getName()
        }

//        viewHolder.card.setOnClickListener {
//            val newIntent = Intent(it.context, SpaceActivity::class.java)
//            newIntent.putExtra("spaceId", dataSet.get(position).getName())
//            it.context.startActivity(newIntent)
//        }
    }

    override fun getItemCount(): Int = dataSet.size
}