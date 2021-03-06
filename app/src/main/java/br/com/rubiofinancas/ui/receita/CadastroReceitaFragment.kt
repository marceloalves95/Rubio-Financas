package br.com.rubiofinancas.ui.receita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rubiofinancas.R
import br.com.rubiofinancas.data.domain.Receita
import br.com.rubiofinancas.databinding.FragmentCadastroReceitaBinding
import br.com.rubiofinancas.ui.base.BaseFragment
import br.com.rubiofinancas.ui.main.MainActivity
import br.com.rubiofinancas.viewmodel.ReceitaViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CadastroReceitaFragment : BaseFragment() {

    private var _binding: FragmentCadastroReceitaBinding? = null
    lateinit var mesCompleto: String
    lateinit var diaHoje: String
    var anoCorrente: Int = 0
    private val binding get() = _binding!!
    private lateinit var dataHoje: String
    private lateinit var item: String
    private lateinit var categoria: String
    private lateinit var valor: String
    private lateinit var mes: String
    private lateinit var ano: String

    private val args by navArgs<CadastroReceitaFragmentArgs>()

    private val viewModel:ReceitaViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        (activity as MainActivity).supportActionBar?.setTitle(R.string.menu_cadastro_receita)

        _binding = FragmentCadastroReceitaBinding.inflate(layoutInflater, container, false)

        initCampos()

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun initCampos() {

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

    private fun initDatas() {

        val localDate = LocalDate.now()
        val mes = localDate.month
        anoCorrente = localDate.year

        val local = Locale("pt", "BR")
        //Aqui ele formata o m??s setado correspondente
        val mesFormatado = DateTimeFormatter.ofPattern("MMMM", local)

        //Aqui ele transforma a primeira letra do m??s em maiuscula
        mesCompleto = mesFormatado.format(mes).capitalize(Locale.ROOT)

        val dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
        diaHoje = dataFormatada.format(localDate)

        binding.dataHoje.setText(diaHoje)
        binding.mes.setText(mesCompleto)
        binding.ano.setText(anoCorrente.toString())


    }
    private fun initEditText(){

        dataHoje = binding.dataHoje.editableText.toString().trim()
        item = binding.item.editableText.toString().trim()
        categoria = binding.categoria.editableText.toString().trim()
        valor = binding.valor.editableText.toString().trim()
        mes = binding.mes.editableText.toString().trim()
        ano = binding.ano.editableText.toString().trim()

    }
    private fun salvar() {

        initEditText()

        if (validarCampos()) {

            val receita = Receita(0, dataHoje, item, categoria, valor.toDouble(), mes, ano.toInt(), false)
            viewModel.adicionarReceita(receita)
            mensagemAtualizada("Receita cadastrada com sucesso")
            findNavController().navigate(R.id.action_cadastro_receita_to_nav_receita)

        }

    }

    private fun atualizar() {

        if (validarCampos()){

            val receita = Receita(args.id, dataHoje, item, categoria, valor.toDouble(), mes, ano.toInt(), false)
            viewModel.atualizarReceita(receita)
            mensagemAtualizada("Receita atualizada com sucesso")
            findNavController().navigate(R.id.action_cadastro_receita_to_nav_receita)

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




}