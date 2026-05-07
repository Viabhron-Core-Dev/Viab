package com.nexus.launcher.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexus.launcher.R
import com.nexus.launcher.data.NexusItem
import com.nexus.launcher.ui.utils.VisualUtils

/**
 * LauncherAdapter: Shared adapter for both App Drawer and Desktop.
 * Implements "Wiggle" edit mode detection.
 */
class LauncherAdapter(
    private var items: List<NexusItem>,
    private val onItemClick: (NexusItem) -> Unit,
    private val onItemLongClick: (NexusItem, View) -> Unit
) : RecyclerView.Adapter<LauncherAdapter.ViewHolder>() {

    private var isEditMode = false

    fun setEditMode(active: Boolean) {
        isEditMode = active
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_launcher_app, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, isEditMode)
        
        holder.itemView.setOnClickListener { onItemClick(item) }
        holder.itemView.setOnLongClickListener { 
            onItemLongClick(item, it)
            true
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val iconView: ImageView = view.findViewById(R.id.item_icon)
        private val labelView: TextView = view.findViewById(R.id.item_label)

        fun bind(item: NexusItem, isEditMode: Boolean) {
            labelView.text = item.label
            // Icon binding logic here...
            
            if (isEditMode) {
                VisualUtils.startWiggle(itemView)
            } else {
                VisualUtils.stopWiggle(itemView)
            }
        }
    }
}
