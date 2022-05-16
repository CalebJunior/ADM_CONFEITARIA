package com.example.app_firebase.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_firebase.Model.Funcionario;
import com.example.app_firebase.Model.Pedidos;
import com.example.app_firebase.PedidosAdapter;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity  {
    private RecyclerView recyclerView;
    List<Pedidos> Pedido;
    PedidosAdapter pedidosAdapter;

    private DatabaseReference bd = config.getbd();
    private DatabaseReference pd = bd.child("Pedido");
    Query pedidos = pd.orderByChild("Status").equalTo(1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView= findViewById(R.id.Recycle_list_pedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Pedido = new ArrayList<>();



        pedidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dados : snapshot.getChildren()){
                    Pedidos P =dados.getValue(Pedidos.class);
                    Pedido.add(P);
                }
                pedidosAdapter = new PedidosAdapter(Pedido);
                recyclerView.setAdapter(pedidosAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this,"Erro de consulta de Pedidos",Toast.LENGTH_SHORT).show();

            }
        });

    }

}