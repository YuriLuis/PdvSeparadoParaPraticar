package com.example.pdvseparado.cliente;

import com.example.pdvseparado.activity.CadastroClienteActivity;
import com.example.pdvseparado.dao.ClienteDAO;
import com.example.pdvseparado.enums.TipoDeCliente;
import com.example.pdvseparado.model.Endereco;
import com.example.pdvseparado.model.cliente.Cliente;

import org.junit.Assert;
import org.junit.Test;

public class ClienteTest {

    @Test
    public void cadastraClienteCasoNaoTenhaNenhumDadoBrancoOuIncorreto(){

        CadastroClienteActivity cadastroClienteActivity = new CadastroClienteActivity();
        ClienteDAO clienteDAO = new ClienteDAO();
        String nome = "Yuri";
        String cpfCnpj = "";
        String cep = "45612567";
        String rua = "Jose isidoro Correia";
        String bairro = "Ponta Aguda";
        String cidade = "Blumenau";
        String estado = "Santa Catarina";
        String numero = "4564";
        String telefone = "33225566";
        String email = "yuri@gmail.com";
        String cdd  = "Cdd Blumenau";

        Endereco endereco = new Endereco(rua,numero,bairro,cidade,estado,cep);
        Cliente cliente = new Cliente(nome,telefone,email,cdd,endereco, TipoDeCliente.FISICO);

        cadastroClienteActivity.adicionaCliente(cliente);

        Assert.assertEquals(1,  clienteDAO.getListCliente().size());
    }
}

