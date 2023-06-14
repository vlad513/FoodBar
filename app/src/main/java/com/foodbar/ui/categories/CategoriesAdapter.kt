package com.foodbar.ui.categories

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
import com.foodbar.databinding.ItemCategorisBinding
import java.util.*

class CategoriesAdapter(private var callback1: (Categories) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.RepozHolder>() {
    private val repozList = ArrayList<Categories?>()

    class RepozHolder(itemView: View, private var callback1: (Categories) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCategorisBinding.bind(itemView)


        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(item: Categories?) = with(binding) {
            image.load(item?.image_url) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            textView.text = item?.name
            cardButton.setOnClickListener {
                callback1.invoke(item!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepozHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categoris, parent, false)
        return RepozHolder(view, callback1)
    }

    override fun onBindViewHolder(holder: RepozHolder, position: Int) {
        holder.bind(repozList[position])
    }

    override fun getItemCount(): Int {
        return repozList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addRepoz(repoz: List<Categories?>) {
        repozList.clear()
        repozList.addAll(repoz)
        notifyDataSetChanged()
    }
}