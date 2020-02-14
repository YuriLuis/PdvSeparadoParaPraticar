package com.example.pdvseparado.controller;

import com.example.pdvseparado.dao.ClienteDAO;
import com.example.pdvseparado.model.cliente.Cliente;

import java.util.List;

public class ClienteController {

    private static ClienteDAO clienteDAO = new ClienteDAO();

    public void save(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    public List<Cliente> getClientes() {
        return clienteDAO.getListCliente();
    }
}
