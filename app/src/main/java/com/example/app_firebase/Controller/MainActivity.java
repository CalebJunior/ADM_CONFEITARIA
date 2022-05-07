package com.example.app_firebase.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_firebase.Model.Funcionario;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText senha;
    Button btnlogar;
    private Funcionario funcionario;
    private FirebaseAuth auth;

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

        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(this,"Dados não preenchidos",Toast.LENGTH_SHORT).show();
        }
        else{
            funcionario = new Funcionario();
            funcionario.setEmail(email);
            funcionario.setPassword(pass);
            funcionario.setNome("Giga");
            logarFunc();
        }


    }

    public void logarFunc(){
        auth = config.getAutenticacao();
        auth.signInWithEmailAndPassword(funcionario.getEmail(),funcionario.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Dados aceitos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this , Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Dados não aceitos",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}