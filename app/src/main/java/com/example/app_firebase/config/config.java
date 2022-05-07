package com.example.app_firebase.config;

import com.google.firebase.auth.FirebaseAuth;

public class config {
    private static FirebaseAuth autenticacao;

    public static FirebaseAuth getAutenticacao() {
        if(autenticacao==null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
