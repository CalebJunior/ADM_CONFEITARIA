package com.example.app_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_firebase.Model.Clientes;
import com.example.app_firebase.Model.ItensPedidos;
import com.example.app_firebase.Model.Pedidos;
import com.example.app_firebase.Model.Produtos;

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
        vhClass.txtnome.setText(pedidos.getID_Cliente());
        vhClass.txtBairro.setText(pedidos.getDt_pedido());

    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView txtnome;
        TextView txtRua;
        TextView txtBairro;
        TextView txtdtEntrega;
        TextView itensPedidos;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            txtnome = itemView.findViewById(R.id.txtCliente);
            txtRua = itemView.findViewById(R.id.txtRua);
            txtBairro = itemView.findViewById(R.id.txtBairro);
            txtdtEntrega = itemView.findViewById(R.id.txtDt_entrega);
            itensPedidos =itemView.findViewById(R.id.txtItemPedido);
        }
    }
}
