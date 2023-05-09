package com.example.myapplication_caching.ui.screens

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.example.myapplication_caching.R
import com.example.myapplication_caching.databinding.FragmentStartBinding
import com.example.myapplication_caching.ui.view_model.AppViewModel
import kotlinx.coroutines.launch


class StartFragment : Fragment() {

    private val viewModel : AppViewModel by activityViewModels ()
    private lateinit var binding : FragmentStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_start, container, false)

        // Inflate the layout for this fragment
        val mProgressDialog = ProgressDialog(requireContext())
        mProgressDialog.setTitle("Download Dialog")
        mProgressDialog.setMessage("Please wait until downloading Films complete")

        viewModel.notifyStart()



        binding.getFilmsBt.setOnClickListener {
            mProgressDialog.show()

            lifecycleScope.launch{
                viewModel.getDataFromTwoApis()

            }
        }

        viewModel.notifyDownloadComplete.observe(this.requireActivity(), Observer {

            if (it) {
                mProgressDialog.cancel()

                if (viewModel.task){
                    findNavController().navigate(R.id.action_startFragment_to_filmsFragment)
                }
            }
        })


        return binding.root

    }


}