package com.example.app_firebase.Model;

import android.app.ActivityManager;

public class Clientes {
    private String Nome;
    private String email;
    private int Cpf;
    private String Rua;
    private int N;
    private  String Bairro;


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCpf() {
        return Cpf;
    }

    public void setCpf(int cpf) {
        Cpf = cpf;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }


    public Clientes() {
    }
}
