package com.example.refrigerator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.refrigerator.data.Food
import com.example.refrigerator.databinding.ListItemFoodBinding
import com.example.refrigerator.utilities.InjectorUtils
import com.example.refrigerator.viewmodel.FoodListItemViewModel

class FoodAdapter : ListAdapter<Food, RecyclerView.ViewHolder>(FoodDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodViewHolder(ListItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val food = getItem(position)
        (holder as FoodViewHolder).bind(food)
    }

    class FoodViewHolder(
        private val binding: ListItemFoodBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food) {
            binding.apply {
                viewModel = FoodListItemViewModel(
                    food = item,
                    foodRepository = InjectorUtils.getPlantRepository(context = context)
                )
                executePendingBindings()
            }
        }
    }
}



private class FoodDiffCallback: DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.foodId == newItem.foodId
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}
