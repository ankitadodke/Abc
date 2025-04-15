package com.example.abc.core

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.abc.R
import com.example.abc.databinding.ActivityMainBinding
import com.example.abc.domain.model.ListItem
import com.example.abc.ui.journey.CarouselAdapter
import com.example.abc.ui.journey.ItemListAdapter
import com.example.abc.ui.journey.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var viewModel: MainViewModel

    private var currentListIndex = 0
    private var filteredItems = emptyList<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupImageCarousel()
        setupRecyclerView()
        setupSearchView()
        setupFloatingActionButton()
        loadInitialData()
    }

    private fun setupImageCarousel() {
        val carouselAdapter = CarouselAdapter(viewModel.imageUrls)
        binding.viewPager.adapter = carouselAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // Setup tab indicator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
            // No text needed for the tabs
        }.attach()
    }

    private fun setupRecyclerView() {
        itemListAdapter = ItemListAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemListAdapter
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun loadInitialData() {
        filteredItems = viewModel.listData[currentListIndex]
        itemListAdapter.updateList(filteredItems)
    }

    private fun filterList(query: String?) {
        val currentList = viewModel.listData[currentListIndex]
        filteredItems = if (query.isNullOrEmpty()) {
            currentList
        } else {
            currentList.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        itemListAdapter.updateList(filteredItems)
    }

    private fun setupFloatingActionButton() {
        binding.fab.setOnClickListener {
            showStatisticsBottomSheet()
        }
    }

    private fun showStatisticsBottomSheet() {
        val stats = viewModel.calculateStatistics(filteredItems)

        val bottomSheetDialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_stats, null)

        val itemCountText = view.findViewById<TextView>(R.id.itemCountText)
        val topCharacters = view.findViewById<TextView>(R.id.topCharacters)

        // Display statistics
        itemCountText.text = "Total items: ${stats["count"]}"

        @Suppress("UNCHECKED_CAST")
        val topChars = stats["topChars"] as List<Pair<Char, Int>>
        val top3Text = topChars.joinToString("\n") { (char, count) ->
            "$char = $count"
        }

        topCharacters.text = top3Text

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}