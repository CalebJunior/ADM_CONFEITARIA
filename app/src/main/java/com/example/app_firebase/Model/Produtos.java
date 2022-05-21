package com.example.app_firebase.Model;

import androidx.annotation.NonNull;

import com.example.app_firebase.config.config;
import com.example.app_firebase.helper.MyCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Produtos {
    private String Nome;
    private double Valor;
    private String Descricao;
    private String ID;





    public Produtos() {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void readprodutos(MyCallback myCallback){
        DatabaseReference bd = config.getbd();
        DatabaseReference pd = bd.child("Produtos");
        Query produtos = pd.child(ID);
        produtos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Produtos produtos = snapshot.getValue(Produtos.class);
                myCallback.onCallback(produtos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}
