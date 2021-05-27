package br.com.rubiofinancas.ui.despesa

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rubiofinancas.R
import br.com.rubiofinancas.databinding.FragmentDespesaBinding
import br.com.rubiofinancas.viewmodel.DespesaViewModel
import br.com.rubiofinancas.viewmodel.DespesaViewModelFactory
import br.com.rubiofinancas.adapters.DespesaAdapter

class DespesaFragment : Fragment() {

    private lateinit var adapter: DespesaAdapter
    private var _binding: FragmentDespesaBinding? = null
    private val binding get() = _binding!!

    lateinit var application: Application
    val viewModel by lazy {

        ViewModelProvider(this, DespesaViewModelFactory(application)).get(DespesaViewModel::class.java)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        application = requireActivity().application!!
        _binding = FragmentDespesaBinding.inflate(inflater, container, false)

        atualizarLayout()

        binding.fab.setOnClickListener {

            findNavController().navigate(R.id.action_nav_despesa_to_cadastro_despesa)
        }

        return binding.root
    }

    fun atualizarLayout(){

        viewModel.atualizarLista()
        viewModel.listAll.observe(viewLifecycleOwner, {

            adapter = DespesaAdapter(it)

            setupRecyclerView()

        })


    }

    private fun setupRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

    }


}