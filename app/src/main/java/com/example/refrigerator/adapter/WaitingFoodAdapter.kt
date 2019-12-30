package com.example.refrigerator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.refrigerator.R
import com.example.refrigerator.data.Food
import com.example.refrigerator.databinding.ListItemRefrigeratorPlantingBinding
import com.example.refrigerator.utilities.InjectorUtils
import com.example.refrigerator.viewmodel.FoodListItemViewModel

class WaitingFoodAdapter : ListAdapter<Food, RecyclerView.ViewHolder>(
    WaitingFoodDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WaitingFoodViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.list_item_refrigerator_planting,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WaitingFoodViewHolder).bind(getItem(position))
    }

    class WaitingFoodViewHolder(
        private val binding: ListItemRefrigeratorPlantingBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food){
            binding.apply {
                viewModel = FoodListItemViewModel(
                    food = item,
                    foodRepository = InjectorUtils.getPlantRepository(context))
                executePendingBindings()
            }
        }
    }
}

private class WaitingFoodDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

}
