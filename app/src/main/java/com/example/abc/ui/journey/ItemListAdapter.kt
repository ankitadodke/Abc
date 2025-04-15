package com.example.abc.ui.journey

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abc.databinding.ItemListBinding
import com.example.abc.domain.model.ListItem

class ItemListAdapter(private var items: List<ListItem>) :
    RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    fun updateList(newList: List<ListItem>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem) {
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.description
        }
    }
}