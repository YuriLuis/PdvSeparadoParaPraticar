package com.example.pdvseparado.dao;

import com.example.pdvseparado.model.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final static List<Cliente> listaDeClientes = new ArrayList<>();

    public void save(Cliente cliente){
        listaDeClientes.add(cliente);
    }

    public List<Cliente> getListCliente() {

        return  listaDeClientes;
    }
}
