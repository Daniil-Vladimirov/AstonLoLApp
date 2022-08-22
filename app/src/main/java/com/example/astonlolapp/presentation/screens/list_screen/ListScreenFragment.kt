package com.example.astonlolapp.presentation.screens.list_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.astonlolapp.R

class ListScreenFragment : Fragment() {

    companion object {
        fun newInstance() = ListScreenFragment()
    }

    private lateinit var viewModel: ListScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListScreenViewModel::class.java]
        // TODO: Use the ViewModel
    }

}