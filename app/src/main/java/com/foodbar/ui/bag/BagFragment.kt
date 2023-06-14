package com.foodbar.ui.bag

import android.annotation.SuppressLint
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.foodbar.R
import com.foodbar.data.room.model.DishesEntity
import com.foodbar.databinding.FragmentBagBinding
import com.foodbar.ui.categories.CategoriesAdapter
import com.foodbar.ui.dishes_dialog.DishesDialogViewModel


class BagFragment : Fragment() {
    private val viewModel: BagViewModel by lazy {
        ViewModelProvider(this)[BagViewModel::class.java]
    }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BagAdapter ({ callbac1(it)} ,{callbac2(it)})
    }
    private lateinit var binding: FragmentBagBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBagBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            bagRcView.adapter = adapter

        }
        viewModel.readAllBase.observe(viewLifecycleOwner) { it ->
            adapter.addRepoz(it)
            var sum = 0
            it.forEach {item->
                sum += item.price!!* item.count!!
            }
            binding.button.text = "Оплатить ${sum} ₽"
        }
    }

    fun callbac1(it: DishesEntity) {
        viewModel.deleteUser(it)
    }
    fun callbac2(it: DishesEntity) {
        viewModel.updateDishes(it)
    }
}