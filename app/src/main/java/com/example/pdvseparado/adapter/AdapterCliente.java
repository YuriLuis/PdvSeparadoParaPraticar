package com.example.pdvseparado.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdvseparado.R;
import com.example.pdvseparado.model.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.MyViewHoler> {

    private List<Cliente> clientes = new ArrayList<>();

    public AdapterCliente(List<Cliente> listaClientes){
        this.clientes = listaClientes;
    }

    @NonNull
    @Override
    public AdapterCliente.MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDaLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listaclientes, parent, false); // layout|cardView onde esta as dados para listagem
        return new MyViewHoler(itemDaLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCliente.MyViewHoler holder, int position) {

        Cliente cliente = clientes.get(position);
        holder.nome.setText(cliente.getNome());
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        TextView nome;

        public MyViewHoler(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tvNome);
        }
    }
}
