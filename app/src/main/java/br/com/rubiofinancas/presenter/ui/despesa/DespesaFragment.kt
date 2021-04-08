package br.com.rubiofinancas.presenter.ui.despesa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.rubiofinancas.R
import br.com.rubiofinancas.databinding.FragmentDespesaBinding

class DespesaFragment : Fragment() {

    private var _binding:FragmentDespesaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDespesaBinding.inflate(inflater, container, false)


        binding.fab.setOnClickListener {

            findNavController().navigate(R.id.action_nav_despesa_to_cadastro_despesa)
        }

        return binding.root
    }
}