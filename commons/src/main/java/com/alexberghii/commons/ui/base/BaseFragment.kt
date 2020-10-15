package com.alexberghii.commons.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


abstract class BaseFragment(
    @LayoutRes
    private val layoutRes: Int
) : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInitDi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    abstract fun onInitDi()
}