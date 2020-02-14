package com.example.pdvseparado.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.pdvseparado.R;
import com.example.pdvseparado.adapter.AdapterCliente;
import com.example.pdvseparado.controller.ClienteController;
import com.example.pdvseparado.model.cliente.Cliente;
import com.example.pdvseparado.util.OrdenaPorOrdemAlfabetica;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaClienteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Cliente> listaDeClientes = new ArrayList<>();
    private ClienteController clienteController = new ClienteController();
    private ExtendedFloatingActionButton btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_clientes);

        instanciaLayoutComXml();
        ordenaRecyclerViewClientes();
        configuraRecyclerView();
    }

    public void instanciaLayoutComXml(){
        recyclerView = findViewById(R.id.recyclerViewClientes);
        btnCadastrar = findViewById(R.id.floatButtonAdicionarCliente);
    }

    public void ordenaRecyclerViewClientes(){
        OrdenaPorOrdemAlfabetica ordenaPorOrdemAlfabetica = new OrdenaPorOrdemAlfabetica();

        this.listaDeClientes = clienteController.getClientes();
        ordenaPorOrdemAlfabetica.ordenar(listaDeClientes);
    }

    public AdapterCliente configurarAdapter(){

       AdapterCliente adapter = new AdapterCliente( listaDeClientes );

        return adapter;
    }

    public void configuraRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(configurarAdapter()); // Adapter

        /**@setHasFixedSize melhora a performace do recyclerview.*/
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    public void cadatraCliente(View view){
      Intent i = new Intent(ListaClienteActivity.this, CadastroClienteActivity.class);
      startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ordenaRecyclerViewClientes();
        configurarAdapter().notifyDataSetChanged();
    }
}
