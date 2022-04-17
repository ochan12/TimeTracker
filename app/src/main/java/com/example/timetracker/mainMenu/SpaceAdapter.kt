package com.example.timetracker.mainMenu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetracker.R
import com.example.timetracker.space.Space
import com.example.timetracker.space.SpaceActivity

class SpaceAdapter constructor(private val dataSet: List<Space>) :
    RecyclerView.Adapter<SpaceAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spaceName: TextView = view.findViewById(R.id.select_space_space_name)
        val card: CardView = view.findViewById(R.id.item_list_space_card)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_select_space, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.spaceName.text = dataSet.get(position).getName()
        viewHolder.card.setOnClickListener {
            val newIntent = Intent(it.context, SpaceActivity::class.java)
            newIntent.putExtra("spaceId", dataSet.get(position).getId())
            it.context.startActivity(newIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataSet.size
}