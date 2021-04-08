package br.com.rubiofinancas.presenter.ui.receita

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rubiofinancas.R
import br.com.rubiofinancas.databinding.FragmentReceitaBinding
import br.com.rubiofinancas.framework.viewmodel.ReceitaViewModel
import br.com.rubiofinancas.framework.viewmodel.ReceitaViewModelFactory
import br.com.rubiofinancas.presenter.adapters.ReceitaAdapter
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ReceitaFragment : Fragment(){

    private lateinit var adapter: ReceitaAdapter
    private var _binding: FragmentReceitaBinding? = null
    private val binding get() = _binding!!
    var actionMode:ActionMode? = null
    val listarId = mutableListOf<Long>()

    lateinit var application: Application
    val viewModel by lazy {

        ViewModelProvider(this, ReceitaViewModelFactory(application)).get(ReceitaViewModel::class.java)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        application = requireActivity().application!!
        _binding = FragmentReceitaBinding.inflate(inflater, container, false)
        val view = binding.root

        initDatas()
        receitasMes()
        atualizarLayout()

        binding.fab.setOnClickListener {

            findNavController().navigate(R.id.action_nav_receita_to_cadastro_receita)

        }

        return view
    }

    fun initDatas() {

        val hoje = LocalDate.now()
        val local = Locale("pt", "BR")
        val mesReduzido = DateTimeFormatter.ofPattern("MMMM", local)

        val mes = hoje.month
        val ano = hoje.year
        val mesAtual = mesReduzido.format(mes).capitalize(local)

        binding.mesCorrente.text = mesAtual
        binding.anoCorrente.text = ano.toString()


    }

    fun receitasMes() {

        viewModel.maiorValor().observe(viewLifecycleOwner, { it

            if (it != null) binding.valorMaiorReceita.text = "RS ${it.toString()}" else binding.valorMaiorReceita.text = "RS 0.00"
        })

        viewModel.menorValor().observe(viewLifecycleOwner,{

            if (it != null) binding.valorMenorReceita.text = "R$ ${it.toString()}" else binding.valorMenorReceita.text = "R$ 0.00"

        })
        viewModel.mediaValor().observe(viewLifecycleOwner,{

            if (it != null){
                val decimal = BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN)
                binding.valorMediaReceita.text = "R$ ${decimal}"
            } else {
                binding.valorMediaReceita.text = "R$ 0.00"

            }

        })


    }

    fun atualizarLayout() {

        viewModel.atualizarLista()
        viewModel.listAll.observe(viewLifecycleOwner, {

            adapter = ReceitaAdapter(it,this)

            setupRecyclerView()

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        atualizarLayout()

        binding.swipeLayout.setOnRefreshListener {

            binding.swipeLayout.isRefreshing = false

            mensagemAtualizada("Atualização Concluida")

        }




    }

    private fun enableActionMode(position: Int) {

        if (actionMode == null) actionMode = activity?.startActionMode(object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.menuInflater?.inflate(R.menu.menu_delete, menu)
                mode?.title = "Deletar Receitas"

                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                if (item?.itemId == R.id.action_delete){
                    deletarReceitas()
                    mode?.finish()
                    return true

                }
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                adapter.selectedItems.clear()
                adapter.receita
                        .filter { it.selected }
                        .forEach { it.selected = false }

                adapter.notifyDataSetChanged()
                actionMode = null
            }

        })

        adapter.toggleSelection(position)
        val size = adapter.selectedItems.size()
        if (size == 0){
            actionMode?.finish()
        }else{
            actionMode?.subtitle = "$size receita(s) selecionada(s)"
            actionMode?.invalidate()

        }



    }

    private fun mensagemAtualizada(mensagem:String){

        Snackbar.make(requireView(), mensagem, Snackbar.LENGTH_SHORT).show()


    }


    fun deletarReceitas(){

        val list = adapter.receita.filter { it.selected }

        for (i in list){

            listarId.add(i.id)

        }
        viewModel.deletarTodasReceitas(listarId)
        adapter.deletarReceitas()

        mensagemAtualizada("Receita excluida com sucesso")



    }

    private fun setupRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        adapter.onItemLongClick= {

            enableActionMode(it)

        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}