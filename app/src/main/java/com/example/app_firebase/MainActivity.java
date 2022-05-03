package com.example.app_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText senha;
    Button btnlogar;

    Funcionario func = new Funcionario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        senha = findViewById( R.id.edtSenha);
        login = findViewById(R.id.edtEmail);
        btnlogar= findViewById(R.id.btnLogar);
    }


    public void btnLogarOnclick(View view){
        String email = login.getText().toString();
        String pass = senha.getText().toString();

        if(func.validaFunc(email,pass)){

        }
        else{


        }


    }
}