package com.foodbar.ui.main

import android.app.backup.BackupAgent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.foodbar.R
import com.foodbar.data.models.Categories
import com.foodbar.databinding.FragmentMenuBinding
import com.foodbar.ui.bag.BagFragment
import com.foodbar.ui.categories.CategoriesFragment

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrentFragment(CategoriesFragment())
        binding.BottomNavigationBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.main -> {
                    setCurrentFragment(CategoriesFragment())

                }
                R.id.basket -> {
                    setCurrentFragment(BagFragment())

                }
                R.id.account -> {

                }
                R.id.search -> {

                }
            }
            true
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setCurrentFragment(fragment: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.hostMenuFragment, fragment)
            commit()
        }
}