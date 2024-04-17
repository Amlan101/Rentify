package com.example.rentify.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentify.R
import com.example.rentify.adapters.RecentPropertyAdapter
import com.example.rentify.data.Property
import com.example.rentify.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupSearchBar()
        setupAdapters()
        setupFilterChips()
    }

//    private fun setupSearchBar() {
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                performSearch(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//
//        })
//    }

    private fun performSearch(query: String?) {
        binding.searchView.clearFocus()

    }

    private fun setupAdapters() {
        val jsonString = loadJSON(this, "properties.json")
        val properties = parseJSON(jsonString)

        // Setting the Linear Layout Manager to horizontal
        binding.recentlyAddedRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        // Setting the adapter
        binding.recentlyAddedRecyclerView.adapter = RecentPropertyAdapter(properties)

        // Adding a divider between the card elements
        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.item_divider)!!)
        binding.recentlyAddedRecyclerView.addItemDecoration(itemDecoration)

    }

    private fun setupFilterChips() {
        val filterCategories = listOf("All", "2BHK", "3BHK", "1BHK", "Studio", "Villa", "Apartment")
        filterCategories.forEach{ category ->
            val chip = Chip(this)
            chip.text = category
            chip.isCheckable = true
            chip.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    filterProperties(category)
                } else {
                    showAllProperties()
                }
            }
            binding.chipGroup.addView(chip)
        }
    }

    private fun filterProperties(category: String) {

    }

    private fun showAllProperties() {
        
    }

    private fun loadJSON(context: Context, fileName: String): String{
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseJSON(jsonString: String): List<Property>{
        val properties = mutableListOf<Property>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(i)
            val property = Property(
                jsonObject.getString("propertyName"),
                jsonObject.getString("type"),
                jsonObject.getString("listingDate"),
                jsonObject.getString("location"),
                jsonObject.getDouble("rating"),
                jsonObject.getInt("price")
            )
            properties.add(property)
        }
        return properties
    }

}