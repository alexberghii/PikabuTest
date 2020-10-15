package com.alexberghii.commons.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun <VM : ViewModel> Fragment.viewModel(
    key: String? = null,
    factory: () -> VM
): VM {
    val factoryViewModel = factory()
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factoryViewModel as T
        }
    }

    return if (key != null) {
        ViewModelProvider(this, viewModelProviderFactory).get(key, factoryViewModel::class.java)
    } else {
        ViewModelProvider(this, viewModelProviderFactory).get(factoryViewModel::class.java)
    }
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, {
        it?.let { t -> observer(t) }
    })
}