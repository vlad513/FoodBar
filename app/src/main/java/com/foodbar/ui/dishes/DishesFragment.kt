package com.foodbar.ui.dishes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodbar.App
import com.foodbar.R
import com.foodbar.data.models.Categories
import com.foodbar.data.models.Dishes
import com.foodbar.databinding.FragmentDishesBinding

import com.foodbar.ui.categories.CategoriesFragment
import com.foodbar.ui.main.MenuFragmentDirections
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DishesFragment : Fragment() {
    private val viewModelServer: DishesViewModel by lazy {
        App.appComponent.dishesviewmodel()
    }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        DishesAdapter { callbac1(it) }
    }
    private lateinit var binding: FragmentDishesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDishesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.hostMenuFragment, CategoriesFragment())
                commit()
            }
        }
        binding.dishesRcView.adapter = adapter
        viewModelServer.selection=0
        viewModelServer.getDishes()

        viewModelServer.dishesByLiveData.observe(viewLifecycleOwner) {
            adapter.addRepoz(it!!.dishes)
        }
        binding.swipeRefresh.setOnRefreshListener {
            if (binding.swipeRefresh.isRefreshing) {
                binding.swipeRefresh.isRefreshing = false
            }
            viewModelServer.selection=binding.tabLayout.selectedTabPosition.toInt()
            viewModelServer.getDishes()
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(requireContext(),binding.tabLayout.selectedTabPosition.toString() , Toast.LENGTH_SHORT).show()
                viewModelServer.selection=binding.tabLayout.selectedTabPosition.toInt()
                viewModelServer.getDishes()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun callbac1(it: Dishes) {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToDishesDialogFragment(it))
    }
}