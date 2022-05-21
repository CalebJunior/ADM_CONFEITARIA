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




    public PedidosAdapter(List<Pedidos> pedidos, Context context,List<String>IDs) {
        this.pedidosList = pedidos;
        this.IDS_pedidos = IDs;
        this.Mycontext = context;
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


        DatabaseReference pd = bd.child("Produtos");
        DatabaseReference id = bd.child("Itens_pedidos");
        Query itens = id.orderByChild("ID_Pedido").equalTo(IDS_pedidos.get(position));
        List<ItensPedidos> itensPedidosList = new ArrayList<ItensPedidos>();
        List<Produtos> produtosList = new ArrayList<Produtos>();
        StringBuilder stringBuilder = new StringBuilder();

        itens.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for(DataSnapshot dados : snapshot.getChildren()){

                    ItensPedidos p = dados.getValue(ItensPedidos.class);
                    itensPedidosList.add(p);

                    Produtos produtos = new Produtos();
                    produtos.setID(p.getID_Produto());
                    produtos.readprodutos(new MyCallback() {
                        @Override
                        public void onCallback(Produtos produtos) {
                            produtosList.add(produtos);
                            stringBuilder.append("\t -"+produtos.getNome()+" "+String.valueOf(p.getQtd())+"x"+String.valueOf(produtos.getValor())+" = "+String.valueOf(p.getValor()+"\n"));
                            if(produtosList.size()==itensPedidosList.size()){
                                vhClass.itensPedidos.setText(stringBuilder);

                            }
                        }
                    });


                }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //Configurando Botão
        vhClass.btn_entregue.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Log.i("TESTE", String.valueOf(IDS_pedidos.size()));



                DatabaseReference pd = bd.child("Pedido").child(IDS_pedidos.get(position));
                Pedidos P = pedidosList.get(position);
                P.setStatus(0);
                pd.setValue(P);

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
