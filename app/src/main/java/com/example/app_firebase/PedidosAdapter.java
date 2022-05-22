package com.example.app_firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_firebase.Controller.Home;
import com.example.app_firebase.Model.Clientes;
import com.example.app_firebase.Model.ItensPedidos;
import com.example.app_firebase.Model.Pedidos;
import com.example.app_firebase.Model.Produtos;
import com.example.app_firebase.config.config;
import com.example.app_firebase.helper.MyCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter {

    DatabaseReference bd = config.getbd();
    List<Pedidos> pedidosList;
    Context Mycontext;
    List<String> IDS_pedidos;




    public PedidosAdapter(List<Pedidos> pedidos, Context context, List<String> IDS) {
        this.pedidosList = pedidos;
        this.Mycontext = context;
        this.IDS_pedidos = IDS;
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
        vhClass.txtnome.setText(pedidos.getNome());
        vhClass.txtdtEntrega.setText(pedidos.getDtaEntrega());
        vhClass.txtRua.setText(pedidos.getRua());
        vhClass.txtBairro.setText(pedidos.getBairro());


        //Configurando Botão
        vhClass.btn_entregue.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Log.i("TOTALDIFF", String.valueOf(IDS_pedidos.size()));

                DatabaseReference pd = bd.child("pedidos").child(IDS_pedidos.get(position)).child("status");
                pd.setValue("0");

                Intent intent = new Intent(v.getContext(), Home.class) ;
                v.getContext().startActivity(intent);
                ((Activity)Mycontext).finish();
                ((Activity)Mycontext).overridePendingTransition(0,0);
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
