package com.example.rentify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentify.R
import com.example.rentify.data.Property

class RecentPropertyAdapter(private val properties: List<Property>): RecyclerView.Adapter<RecentPropertyAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val propertyName: TextView = view.findViewById(R.id.propertyName)
        val propertyAddress: TextView = view.findViewById(R.id.propertyAddress)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recently_added_row_item, parent, false)

        // Adjusting the display metrics to display 80% of one card and 20% another card
        val displayMetrics = parent.context.resources.displayMetrics
        val itemWidth = (displayMetrics.widthPixels * 0.8).toInt()
        view.layoutParams = ViewGroup.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = properties[position]
        holder.propertyName.text = property.propertyName
        holder.propertyAddress.text = property.location
    }

    override fun getItemCount(): Int {
        return properties.size
    }
}