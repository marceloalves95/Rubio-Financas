package br.com.rubiofinancas.ui.despesa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.rubiofinancas.R
import br.com.rubiofinancas.databinding.FragmentCadastroDespesaBinding
import br.com.rubiofinancas.ui.base.BaseFragment

class CadastroDespesaFragment : BaseFragment() {

    private var _binding:FragmentCadastroDespesaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCadastroDespesaBinding.inflate(layoutInflater,container,false)

        val list = arrayListOf("Cartão", "Dinheiro")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        binding.pagamento.setAdapter(adapter)

        binding.pagamento.setOnItemClickListener { parent, view, position, id ->

          when(position){

              0 ->  binding.inputPagamento.setStartIconDrawable(R.drawable.contactless_payment_circle_outline)
              1 ->  binding.inputPagamento.setStartIconDrawable(R.drawable.cash)
          }


        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}