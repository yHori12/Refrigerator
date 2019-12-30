package com.example.refrigerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.refrigerator.R
import com.example.refrigerator.adapter.WaitingFoodAdapter
import com.example.refrigerator.databinding.FragmentRefrigeratorBinding
import com.example.refrigerator.utilities.InjectorUtils
import com.example.refrigerator.viewmodel.RefrigeratorViewModel


class RefrigeratorFragment : Fragment() {

    private lateinit var binding:FragmentRefrigeratorBinding

    private val viewModel: RefrigeratorViewModel by viewModels {
        InjectorUtils.provideRefrigeratorViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRefrigeratorBinding.inflate(inflater,container,false)
        val waitingFoodAdapter = WaitingFoodAdapter()
        binding.refrigeratorFoodList.apply {
            this.adapter = waitingFoodAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.addPlant.setOnClickListener {
            navigateToFoodListPager()
        }

        subscribeUi(waitingFoodAdapter,binding)
        return binding.root
    }

    private fun subscribeUi(adapter: WaitingFoodAdapter, binding: FragmentRefrigeratorBinding){
        viewModel.waitingFoods.observe(viewLifecycleOwner){ result ->
            adapter.submitList(result)
        }
    }

    private fun navigateToFoodListPager(){
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            FOOD_LIST_PAGE_INDEX
    }

}
