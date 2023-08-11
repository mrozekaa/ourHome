package com.mrozeka.naszdom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrozeka.naszdom.databinding.FragmentHomeBinding
import com.mrozeka.naszdom.firebase.FirRepository
import com.mrozeka.naszdom.helper.DialogHelper
import com.mrozeka.naszdom.pref.PrefRepository

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(PrefRepository(requireContext()), FirRepository())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.state.observe(viewLifecycleOwner) {
          when(it){
              is HomeViewModel.State.ShowHomeIdDialog ->{ DialogHelper.withForceEditText(requireContext(),
                  layoutInflater, it.title, it.buttonTitle){id->
                  homeViewModel.checkAndSaveHomeId(id)
              }}
              is HomeViewModel.State.FillView ->{
                  binding.textHome.text = it.house.title
              }
              is HomeViewModel.State.OnError -> {
                  DialogHelper.withError(requireContext(), layoutInflater,it.msg){
                      homeViewModel.onErrorClosed()
                  }
              }
              else -> {}
          }
        }
        homeViewModel.fillView()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}