package com.foodbar.ui.bag

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.foodbar.R
import com.foodbar.data.models.Categories
import com.foodbar.data.models.Dishes
import com.foodbar.data.room.model.DishesEntity
import com.foodbar.databinding.ItemBagBinding
import com.foodbar.databinding.ItemCategorisBinding
import com.foodbar.databinding.ItemDishesBinding
import java.util.*

class BagAdapter(private var callback1: (DishesEntity) -> Unit,private var callback2: (DishesEntity) -> Unit) :
    RecyclerView.Adapter<BagAdapter.RepozHolder>() {
    private val repozList = ArrayList<DishesEntity?>()

    class RepozHolder(itemView: View, private var callback1: (DishesEntity) -> Unit,private var callback2: (DishesEntity) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBagBinding.bind(itemView)


        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(item: DishesEntity?) = with(binding) {
            image.load(item?.image_url) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            title.text=item?.name
            sum.text="${item?.price} ₽ · ${item?.weight}г"
            binding.txtNumbers.text = item?.count.toString()
            binding.imgMinus.setOnClickListener {
                callback1.invoke(DishesEntity(item!!.id,item.name,item.price,item.weight,item.description,item.image_url, item.count?.minus(1)))
            }
            binding.imgPlus.setOnClickListener {
                callback2.invoke(DishesEntity(item!!.id,item.name,item.price,item.weight,item.description,item.image_url, item.count?.plus(1)))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepozHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bag, parent, false)
        return RepozHolder(view, callback1,callback2)
    }

    override fun onBindViewHolder(holder: RepozHolder, position: Int) {
        holder.bind(repozList[position])
    }

    override fun getItemCount(): Int {
        return repozList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addRepoz(repoz: List<DishesEntity>) {
        repozList.clear()
        repozList.addAll(repoz)
        notifyDataSetChanged()
    }
}