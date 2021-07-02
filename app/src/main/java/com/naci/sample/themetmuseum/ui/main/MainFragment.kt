package com.naci.sample.themetmuseum.ui.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.naci.sample.themetmuseum.R
import com.naci.sample.themetmuseum.common.BaseFragment
import com.naci.sample.themetmuseum.data.Resource
import com.naci.sample.themetmuseum.databinding.FragmentObjectListBinding
import com.naci.sample.themetmuseum.util.viewbindingdelegation.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_object_list) {

    @Inject
    lateinit var testFragmentManager: FragmentManager

    private val binding by viewBinding(FragmentObjectListBinding::bind)

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var listAdapter: ObjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ObjectAdapter(ObjectInfoListener { objectInfo ->
            Toast.makeText(
                requireContext(),
                "Object Clicked : $objectInfo", Toast.LENGTH_SHORT
            ).show()
        })
        prepareRecyclerView()
        setupObservers()

        lifecycleScope.launchWhenStarted {
            viewModel.getObjectInfo((1..10000).random())
            viewModel.getObjectInfo((1..10000).random())
            viewModel.getObjectInfo((1..10000).random())
            delay(200)
            viewModel.getObjectInfo((1..10000).random())
            delay(600)
            viewModel.getObjectInfo((1..10000).random())
            delay(500)
            viewModel.getObjectInfo((1..10000).random())
        }
    }

    private fun prepareRecyclerView() {
        binding.recyclerViewObjects.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            itemAnimator = DefaultItemAnimator()
//            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.progressState.collect { isLoading ->
                binding.progressLoading.visibility = if (isLoading) VISIBLE else GONE
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is Resource.Success -> {
                        Timber.d("Success ${uiState.data}")
                        listAdapter.submitList(uiState.data)
                    }
                    is Resource.Error -> {
                        Timber.e("Error ${uiState.apiError}")
                    }
                    else -> {
                    }
                }
            }
        }


    }

}