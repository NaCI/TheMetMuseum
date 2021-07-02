package com.naci.sample.themetmuseum.util.viewbindingdelegation

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


// https://medium.com/@Zhuinden/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c
/**
 * A delegation function that initializes [ViewBinding] for desired [Fragment]
 *
 * @param viewBindingFactory The factory to initialize binding
 */
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

/**
 * A delegation function that initializes [ViewBinding] for desired [AppCompatActivity]
 *
 * @param bindingInflater The inflater to inflate view
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }