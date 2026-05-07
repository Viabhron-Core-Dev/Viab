package com.nexus.launcher.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexus.launcher.R
import com.nexus.launcher.logic.AppLoader
import com.nexus.launcher.ui.adapters.LauncherAdapter

/**
 * AppDrawerFragment: The scrollable list of all installed apps.
 * Features a search bar and fast-scroll logic.
 */
class AppDrawerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LauncherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_app_drawer, container, false)
        recyclerView = root.findViewById(R.id.drawer_recycler)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        
        val apps = AppLoader(requireContext()).loadAllApps()
        adapter = LauncherAdapter(apps, { item ->
            // Launch App
        }, { item, view ->
            // Enter Edit Mode or show Menu
        })
        
        recyclerView.adapter = adapter
        return root
    }
}
