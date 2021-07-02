package com.naci.sample.themetmuseum.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.naci.sample.themetmuseum.common.BaseActivity
import com.naci.sample.themetmuseum.databinding.ActivityMainBinding
import com.naci.sample.themetmuseum.util.viewbindingdelegation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}