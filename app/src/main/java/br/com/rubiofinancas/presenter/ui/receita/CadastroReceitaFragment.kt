package br.com.rubiofinancas.presenter.ui.receita

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rubiofinancas.R
import br.com.rubiofinancas.data.domain.Receita
import br.com.rubiofinancas.databinding.FragmentCadastroReceitaBinding
import br.com.rubiofinancas.framework.viewmodel.ReceitaViewModel
import br.com.rubiofinancas.framework.viewmodel.ReceitaViewModelFactory
import br.com.rubiofinancas.presenter.ui.base.BaseFragment
import br.com.rubiofinancas.presenter.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CadastroReceitaFragment : BaseFragment() {

    private var _binding: FragmentCadastroReceitaBinding? = null
    lateinit var mesCompleto: String
    lateinit var diaHoje: String
    var anoCorrente: Int = 0
    private val binding get() = _binding!!
    lateinit var application: Application
    private lateinit var dataHoje: String
    lateinit var item: String
    lateinit var categoria: String
    lateinit var valor: String
    lateinit var mes: String
    lateinit var ano: String

    private val args by navArgs<CadastroReceitaFragmentArgs>()

    val viewModel by lazy {

        ViewModelProvider(this, ReceitaViewModelFactory(application)).get(ReceitaViewModel::class.java)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        application = requireActivity().application!!
        (activity as MainActivity).supportActionBar?.setTitle(R.string.menu_cadastro_receita)

        _binding = FragmentCadastroReceitaBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        initCampos()


        return view

    }



    fun initDatas() {

        val localDate = LocalDate.now()
        val mes = localDate.month
        anoCorrente = localDate.year

        val local = Locale("pt", "BR")
        //Aqui ele formata o mês setado correspondente
        val mesFormatado = DateTimeFormatter.ofPattern("MMMM", local)

        //Aqui ele transforma a primeira letra do mês em maiuscula
        mesCompleto = mesFormatado.format(mes).capitalize(Locale.ROOT)

        val dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
        diaHoje = dataFormatada.format(localDate)

        binding.dataHoje.setText(diaHoje)
        binding.mes.setText(mesCompleto)
        binding.ano.setText(anoCorrente.toString())


    }

    fun initEditText(){

        dataHoje = binding.dataHoje.editableText.toString().trim()
        item = binding.item.editableText.toString().trim()
        categoria = binding.categoria.editableText.toString().trim()
        valor = binding.valor.editableText.toString().trim()
        mes = binding.mes.editableText.toString().trim()
        ano = binding.ano.editableText.toString().trim()

    }

    fun salvar() {

        initEditText()

        if (validarCampos()) {

            val receita = Receita(0, dataHoje, item, categoria, valor.toDouble(), mes, ano.toInt(), false)
            viewModel.adicionarReceita(receita)
            mensagemAtualizada("Receita cadastrada com sucesso")
            findNavController().navigate(R.id.action_cadastro_receita_to_nav_receita)

        }

    }

    fun atualizar() {

        if (validarCampos()){

            val receita = Receita(args.id, dataHoje, item, categoria, valor.toDouble(), mes, ano.toInt(), false)
            viewModel.atualizarReceita(receita)
            mensagemAtualizada("Receita atualizada com sucesso")
            findNavController().navigate(R.id.action_cadastro_receita_to_nav_receita)

        }

    }

    fun initCampos() {

        if (args.receita == null) {

            initDatas()
            binding.cadastrarAtualizarReceita.setCompoundDrawablesWithIntrinsicBounds(R.drawable.content_save, 0, 0, 0)
            binding.cadastrarAtualizarReceita.setText(R.string.cadastrar_receita)
            binding.item.requestFocus()

            binding.cadastrarAtualizarReceita.setOnClickListener {

                salvar()


            }

        } else {

            (activity as MainActivity).supportActionBar?.setTitle(R.string.atualizacao_receita)
            binding.dataHoje.setText(args.receita?.data_hoje)
            binding.item.setText(args.receita?.item)
            binding.categoria.setText(args.receita?.categoria)
            binding.valor.setText(args.receita?.valor.toString())
            binding.mes.setText(args.receita?.mes)
            binding.ano.setText(args.receita?.ano.toString())
            binding.cadastrarAtualizarReceita.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_update_white, 0, 0, 0)
            binding.cadastrarAtualizarReceita.setText(R.string.atualizacao_receita)

            binding.cadastrarAtualizarReceita.setOnClickListener {

                atualizar()

            }



        }

    }

    private fun mensagemAtualizada(mensagem: String) {

        Snackbar.make(requireView(), mensagem, Snackbar.LENGTH_SHORT).show()


    }

    private fun validarCampos(): Boolean {

        var validador = true

        initEditText()

        val mensagemErro = "Preencha o campo corretamente"

        when{
            item.isEmpty() && categoria.isEmpty() && valor.isEmpty() -> {

                binding.campoItem.error = mensagemErro
                binding.campoCategoria.error = mensagemErro
                binding.campoValor.error = mensagemErro

                validador = false
                return validador
            }
            item.isNotEmpty() && categoria.isEmpty() && valor.isEmpty() -> {

                binding.campoCategoria.error = mensagemErro
                binding.campoValor.error = mensagemErro

                validador = false
                return validador
            }
            item.isEmpty() && categoria.isNotEmpty() && valor.isEmpty() -> {

                binding.campoItem.error = mensagemErro
                binding.campoValor.error = mensagemErro

                validador = false
                return validador
            }
            item.isEmpty() && categoria.isEmpty() && valor.isNotEmpty() ->{

                binding.campoItem.error = mensagemErro
                binding.campoCategoria.error = mensagemErro

                validador = false
                return validador
            }
            item.isEmpty() && categoria.isNotEmpty() && valor.isNotEmpty() -> {

                binding.campoItem.error = mensagemErro

                validador = false
                return validador
            }
            categoria.isEmpty() && item.isNotEmpty() && valor.isNotEmpty() -> {

                binding.campoCategoria.error = mensagemErro

                validador = false
                return validador
            }
            valor.isEmpty() && item.isNotEmpty() && categoria.isNotEmpty() -> {

                binding.campoValor.error = mensagemErro
                validador = false
                return validador
            }

        }
        return validador

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}