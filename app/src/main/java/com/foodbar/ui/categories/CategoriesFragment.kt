package com.foodbar.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foodbar.App
import com.foodbar.R
import com.foodbar.data.models.Categories

import com.foodbar.databinding.FragmentCategoriesBinding
import com.foodbar.ui.dishes.DishesFragment

class CategoriesFragment : Fragment() {
    private val viewModelServer: CategoriesViewModel by lazy {
        App.appComponent.categoriesviewmodel()
    }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoriesAdapter { callbac1(it) }
    }
    private lateinit var binding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.criticsRcView.adapter = adapter
        viewModelServer.getCategories()

        viewModelServer.categotiesByLiveData.observe(viewLifecycleOwner) {
            adapter.addRepoz(it!!.сategories)
        }
        binding.swipeRefresh.setOnRefreshListener {
            if (binding.swipeRefresh.isRefreshing) {
                binding.swipeRefresh.isRefreshing = false
            }
            viewModelServer.getCategories()
        }
    }

    private fun callbac1(it: Categories) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.hostMenuFragment, DishesFragment())
            commit()
    }
}}