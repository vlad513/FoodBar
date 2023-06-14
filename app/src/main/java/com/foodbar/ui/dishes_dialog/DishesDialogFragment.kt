package com.foodbar.ui.dishes_dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.foodbar.App
import com.foodbar.data.room.model.DishesEntity
import com.foodbar.databinding.FragmentDishesDialogBinding



class DishesDialogFragment : DialogFragment() {
    private val viewModelServer: DishesDialogViewModel by lazy{
        ViewModelProvider(this)[DishesDialogViewModel::class.java]
    }
    private val args: DishesDialogFragmentArgs by navArgs()
    private lateinit var binding: FragmentDishesDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDishesDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            image.load(args.dishes.image_url) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            title.text = args.dishes.name
            sum.text = "${args.dishes.price} ₽ · ${args.dishes.weight}г"
            description.text = args.dishes.description
            buttonAddBag.setOnClickListener {
                insertDataBase(
                    args.dishes.id,
                    args.dishes.name,
                    args.dishes.price,
                    args.dishes.weight,
                    args.dishes.description,
                    args.dishes.image_url,
                    1
                )
            }
        }

    }

    private fun insertDataBase(
        id: Int?,
        name: String?,
        price: Int?,
        weight: Int?,
        description: String?,
        image_url: String?,
        count: Int?
    ) {
        val dishes = DishesEntity(id, name, price, weight, description, image_url, count)
        viewModelServer.addDishes(dishes)
        dialog?.dismiss()
    }
}


