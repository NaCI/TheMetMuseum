package com.naci.sample.themetmuseum.data

import androidx.lifecycle.Observer
import com.naci.sample.themetmuseum.common.BaseViewModel

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

class ProgressbarEventObserver<T>(
    private val baseViewModel: BaseViewModel,
    private val onEventUnhandledContent: (T) -> Unit
) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            when (value) {
                is Resource.Loading<*> -> {
                    baseViewModel.increaseInProgressCount()
                }
                is Resource.Success<*> -> {
                    baseViewModel.decreaseInProgressCount()
                }
                is Resource.Failure<*> -> {
                    baseViewModel.decreaseInProgressCount()
                }
            }
            onEventUnhandledContent(value)
        }
    }
}