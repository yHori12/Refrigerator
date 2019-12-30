package com.example.refrigerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.refrigerator.R
import com.example.refrigerator.adapter.FoodAdapter
import com.example.refrigerator.databinding.FragmentFoodListBinding
import com.example.refrigerator.utilities.InjectorUtils
import com.example.refrigerator.viewmodel.FoodListViewModel

class FoodListFragment : Fragment() {
    private val viewModel: FoodListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFoodListBinding.inflate(inflater,container,false)
        context ?: return binding.root

        val adapter = FoodAdapter()
        binding.foodList.adapter = adapter

        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: FoodAdapter) {
        viewModel.foods.observe(viewLifecycleOwner) { foods ->
            adapter.submitList(foods)
        }
    }

    private fun navigateToRefrigeratorPager(){
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            REFRIGERATOR_PAGE_INDEX
    }

}
