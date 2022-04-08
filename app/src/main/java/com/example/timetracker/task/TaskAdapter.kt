package com.example.timetracker.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetracker.R
import org.joda.time.DateTime

class TaskAdapter constructor(private val dataSet: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date = view.findViewById<TextView>(R.id.task_date)
        val time = view.findViewById<TextView>(R.id.task_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_task, parent, false)
        return TaskAdapter.ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TaskAdapter.ViewHolder, position: Int) {
        viewHolder.date.text =
            DateTime(dataSet.get(position).getStartTime()).toLocalDate().toString()
        viewHolder.date.text = dataSet.get(position).getDuration()

//        viewHolder.card.setOnClickListener {
//            val newIntent = Intent(it.context, SpaceActivity::class.java)
//            newIntent.putExtra("spaceId", dataSet.get(position).getName())
//            it.context.startActivity(newIntent)
//        }
    }

    override fun getItemCount(): Int  = dataSet.size
}