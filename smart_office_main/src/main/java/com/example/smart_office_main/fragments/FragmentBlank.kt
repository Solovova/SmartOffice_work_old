package com.example.smart_office_main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smart_office_main.R

class FragmentBlank : FragmentParent() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentBlank()
    }
}
