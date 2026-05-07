package com.nexus.launcher.sidebar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexus.launcher.R

/**
 * SidebarFragment: The UI container for the slide-out sidebar.
 */
class SidebarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sidebar, container, false)
        recyclerView = root.findViewById(R.id.sidebar_recycler)
        
        // Setup Grid with "Stick Mode" logic
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Default 2 cols
        
        return root
    }

    fun enterEditMode() {
        // Toggle 'wiggle' adapters and show 'Trash Zone'
    }
}
