package com.example.pdvseparado.activity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pdvseparado.R;
import com.example.pdvseparado.controller.ClienteController;
import com.example.pdvseparado.enums.TipoDeCliente;
import com.example.pdvseparado.exception.BairroInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.CddInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.CepInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.CidadeInvalidaOuEmBrancoException;
import com.example.pdvseparado.exception.EmailClienteInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.EstadoInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.NomeClienteInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.NumeroInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.RuaInvalidaOuEmBrancoException;
import com.example.pdvseparado.exception.TelefoneClienteInvalidoOuEmBrancoException;
import com.example.pdvseparado.exception.TipoPessoaInvalidoOuNaoImformadoException;
import com.example.pdvseparado.model.cliente.Cliente;
import com.example.pdvseparado.util.Validacoes;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.pdvseparado.enums.TipoDeCliente.FISICO;

public class CadastroClienteActivity extends AppCompatActivity {

    private TextInputEditText nome, cpfCnpj, telefone, rua,
            numero, bairro, cidade, cep, email;
    private ExtendedFloatingActionButton floatButtonAdd, floatButtonCancelar;
    private RadioButton rbFisica, rbJuridica;
    private ClienteController clienteController = new ClienteController();
    private TextWatcher twCpf, twCnpj, twTelefone, twCep;
    private RadioGroup rgTipoPessoa;
    private TextInputLayout textInputNome;
    private TextInputLayout textInputCpfOuCnpj;
    private TextInputLayout inputTextEmail;
    private AutoCompleteTextView auto_estado, auto_cdd;
    private Validacoes validacoes = new Validacoes();
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        instanciaLayoutComXml();
    }

    /**@Autor Yuri Luis Garcia Pereira <yuri.pereira@hbsis.com.br>
     * @Função Mostra todos os Centro de Distribuições cadastrados no sistema
     * para o usuário selecionar o cdd que ele pega seus produtos.*/
    public void populaDropDownCdd(){
        String[] item_siglasEstados = getResources().getStringArray(R.array.CDDs);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, item_siglasEstados);
        auto_cdd.setAdapter(adapter);
    }

    /**@Autor Yuri Luis Garcia Pereira <yuri.pereira@hbsis.com.br>
     * @Função Mostra todos os estados do Brasil para o usuário
     * selecionar o estado onde mora.
     * */
    public void populaDropDownEstados() {
        String[] item_siglasEstados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, item_siglasEstados);
        auto_estado.setAdapter(adapter);
    }

    /**@Autor Yuri Luis Garcia Pereira <yuri.pereira@hbsis.com.br>
     * @Função Atribui para os atributos da activity os id dos
     * componentes que estão na interface.*/
    public void instanciaLayoutComXml() {
        nome                = findViewById(R.id.etNomeCliente);
        rbFisica            = findViewById(R.id.rbFisica);
        rbJuridica          = findViewById(R.id.rbJuridica);
        cpfCnpj             = findViewById(R.id.etCpfOuCnpj);
        rgTipoPessoa        = findViewById(R.id.rgTipoCliente);
        textInputNome       = findViewById(R.id.textInputNome);
        textInputCpfOuCnpj  = findViewById(R.id.textInputCpfOuCnpj);
        inputTextEmail      = findViewById(R.id.inputTextEmail);
        email               = findViewById(R.id.etEmail);
        inputTextEmail      = findViewById(R.id.inputTextEmail);
        telefone            = findViewById(R.id.etFone);
        cep                 = findViewById(R.id.etCep);
        floatButtonAdd      = findViewById(R.id.floatButtonAdd);
        rua                 = findViewById(R.id.etEndereco);
        numero              = findViewById(R.id.etNumero);
        bairro              = findViewById(R.id.etBairro);
        cidade              = findViewById(R.id.etCidade);
        auto_estado         = findViewById(R.id.auto_estado);
        auto_cdd            = findViewById(R.id.auto_cdd);
        floatButtonCancelar = findViewById(R.id.floatButtonCancelar);

        cpfCnpj.setVisibility(View.INVISIBLE);
        populaDropDownEstados();
        populaDropDownCdd();
    }

    public void adicionaCliente(Cliente cliente) throws RuntimeException{

        if (validaSePodeCadastrarCliente(cliente)){

            clienteController.save(cliente);
        }

    }
    public void verifcaSeClienteEhFisicoOuJuridico(Cliente cliente){

        TipoDeCliente tipoDeCliente;

        if (rbFisica.isChecked()){

            tipoDeCliente = FISICO;
            cliente.setTipoDeCliente(tipoDeCliente);
        }else {

            cliente.setTipoDeCliente(TipoDeCliente.JURIDICO);
        }
    }

    public boolean validaSePodeCadastrarCliente(Cliente cliente){

        verifcaSeClienteEhFisicoOuJuridico(cliente);

        boolean retorno = true;

        if (cliente.getTipoDeCliente().equals(null) || cliente.getTipoDeCliente().equals("")){
            retorno = false;
            throw new TipoPessoaInvalidoOuNaoImformadoException("Escolha o tipo de pessoa!");
        }

        if (cliente.getNome().isEmpty() || cliente.getNome().equals("")|| cliente.getNome().equals(null)){
            retorno = false;
            throw new NomeClienteInvalidoOuEmBrancoException("Nome Cliente Inválido");
        }

        if (cliente.getTelefone().isEmpty() || cliente.getTelefone().equals("") || cliente.getTelefone().equals(null)){
            retorno = false;
            throw new TelefoneClienteInvalidoOuEmBrancoException("Telefone Cliente Inválido");
        }

        if (cliente.getEmail().isEmpty() || cliente.getEmail().equals("") || cliente.getEmail().equals(null)){
            retorno = false;
            throw new EmailClienteInvalidoOuEmBrancoException("Email Cliente Inválido!");
        }

        if (cliente.getCdd().isEmpty() || cliente.getCdd().equals("") || cliente.getCdd().equals(null)){
            retorno = false;
            throw new CddInvalidoOuEmBrancoException("Cdd Não Informado!");
        }

        if (cliente.getEndereco().getRua().isEmpty() || cliente.getEndereco().getRua().equals("")
             || cliente.getEndereco().getRua().equals(null)){
            retorno = false;
            throw new RuaInvalidaOuEmBrancoException("Rua não informada");
        }

        if (cliente.getEndereco().getNumero().isEmpty() || cliente.getEndereco().getNumero().equals("")
            || cliente.getEndereco().getNumero().equals(null)){
            retorno = false;
            throw new NumeroInvalidoOuEmBrancoException("Número não informado!");
        }

        if (cliente.getEndereco().getBairro().isEmpty() || cliente.getEndereco().getBairro().equals("")
            || cliente.getEndereco().getBairro().equals(null)){
            retorno = false;
            throw new BairroInvalidoOuEmBrancoException("Bairro não Informado");
        }

        if (cliente.getEndereco().getCidade().isEmpty() || cliente.getEndereco().getCidade().equals("")
            || cliente.getEndereco().getCidade().equals(null)){
            retorno = false;
            throw new CidadeInvalidaOuEmBrancoException("Cidade não informada!");
        }

        if (cliente.getEndereco().getEstado().isEmpty() || cliente.getEndereco().getEstado().equals("")
            || cliente.getEndereco().getEstado().equals(null)){
            retorno = false;
            throw new EstadoInvalidoOuEmBrancoException("Estado não informado!");
        }

        if (cliente.getEndereco().getCep().isEmpty() || cliente.getEndereco().getCep().equals("")
            || cliente.getEndereco().getCep().equals(null)){
            retorno = false;
            throw new CepInvalidoOuEmBrancoException("Cep não informado!");
        }
    return retorno;
    }
}
