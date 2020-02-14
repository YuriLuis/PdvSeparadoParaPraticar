package com.example.pdvseparado.model.cliente;

import com.example.pdvseparado.enums.TipoDeCliente;
import com.example.pdvseparado.model.Endereco;

public class Cliente {

    private String nome;
    private String cpf_cnpj;
    private String telefone;
    private String email;
    private String cdd;
    private Endereco endereco;
    private TipoDeCliente tipoDeCliente;



    public Cliente(String nome, String cpf_cnpj, String telefone, String email, String cdd, Endereco endereco, TipoDeCliente tipoDeCliente) {
        this.nome = nome;
        this.cpf_cnpj = cpf_cnpj;
        this.telefone = telefone;
        this.email = email;
        this.cdd = cdd;
        this.endereco = endereco;
        this.tipoDeCliente = tipoDeCliente;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCdd() {
        return cdd;
    }

    public void setCdd(String cdd) {
        this.cdd = cdd;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoDeCliente getTipoDeCliente() {
        return tipoDeCliente;
    }

    public void setTipoDeCliente(TipoDeCliente tipoDeCliente) {
        this.tipoDeCliente = tipoDeCliente;
    }
}
