package com.example.pdvseparado.util;

import com.example.pdvseparado.model.cliente.Cliente;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenaPorOrdemAlfabetica implements Comparator<Cliente> {
    @Override
    public int compare(Cliente o1, Cliente o2) {
        return o1.getNome().compareToIgnoreCase(o2.getNome());
    }

    public void ordenar(List<Cliente> listaCliente){
        Collections.sort(listaCliente, new OrdenaPorOrdemAlfabetica());
    }
}
