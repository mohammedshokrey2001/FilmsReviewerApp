package com.example.myapplication_caching.ui.screens

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


        viewModel.notifyStart()

        lifecycleScope.launch{}


        binding.getFilmsBt.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            lifecycleScope.launch{
               if (  viewModel.getData()){

               }

            }
        }

        viewModel.notifyDownloadComplete.observe(this.requireActivity(), Observer {

            if (it) {

                binding.progressBar2.visibility = View.GONE
                if (viewModel.task){
                    this.findNavController().navigate(R.id.action_startFragment_to_filmsFragment)
                }else{
                    Toast.makeText(this.requireContext(),"please check internet Connection and wait until download the data",Toast.LENGTH_LONG).show()
                }
            }
        })


        return binding.root
    }

}