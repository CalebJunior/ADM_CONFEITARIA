package com.example.app_firebase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_firebase.Model.Clientes;
import com.example.app_firebase.Model.ItensPedidos;
import com.example.app_firebase.Model.Pedidos;
import com.example.app_firebase.Model.Produtos;
import com.example.app_firebase.config.config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter {


    List<Pedidos> pedidosList;

    public PedidosAdapter( List<Pedidos> pedidos) {

        this.pedidosList = pedidos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido,parent,false);
        ViewHolderClass vhClass =new ViewHolderClass(view);

        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Pedidos pedidos = pedidosList.get(position);

        //pegando os dados do cliente e os colocando na textView
        DatabaseReference bd = config.getbd();
        DatabaseReference cd = bd.child("Clientes");
        Query clientes = cd.child(pedidos.getID_Cliente());

        clientes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Clientes c = snapshot.getValue(Clientes.class);
                vhClass.txtnome.setText(c.getNome());
                vhClass.txtBairro.setText(c.getBairro());
                vhClass.txtRua.setText(c.getRua());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        vhClass.txtdtEntrega.setText(pedidos.getDt_entrega());





        //Configurando Botão
        vhClass.btn_entregue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vhClass.txtBairro.setText("Que merda");
            }
        });


    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView txtnome;
        TextView txtRua;
        TextView txtBairro;
        TextView txtdtEntrega;
        TextView itensPedidos;
        Button btn_entregue;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            txtnome = itemView.findViewById(R.id.txtCliente);
            txtRua = itemView.findViewById(R.id.txtRua);
            txtBairro = itemView.findViewById(R.id.txtBairro);
            txtdtEntrega = itemView.findViewById(R.id.txtDt_entrega);
            itensPedidos =itemView.findViewById(R.id.txtItemPedido);
            btn_entregue = itemView.findViewById(R.id.btn_Entregue);

        }


    }

}
