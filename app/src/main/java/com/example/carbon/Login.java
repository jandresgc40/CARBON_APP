package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.AppTheme);
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.etEmail);
        editTextContrasena = findViewById(R.id.etContrasena);

    }

    public void Registro(View v) {

        if (!editTextEmail.getText().toString().equals("") && !editTextContrasena.getText().toString().equals("")) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail.getText().toString(),
                    editTextContrasena.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        iniciarApp(editTextEmail.getText().toString());

                    } else if (editTextContrasena.getText().length() < 6) {

                        Toast toast = Toast.makeText(Login.this, "La contraseña debe tener como mínimo 6 caracteres", Toast.LENGTH_SHORT);
                        toast.show();

                    } else {
                        Toast toast = Toast.makeText(Login.this, "Error en el registro, indica un correo electrónico válido!", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
            });

        }



    }

    public void InicioSesion(View v) {

        if (editTextEmail.getText().toString().equals("admin") && editTextContrasena.getText().toString().equals("admin")) {

            Intent intent = new Intent(Login.this, Administradores.class);
            startActivity(intent);
            finish();

        } else if (!editTextEmail.getText().toString().equals("") && !editTextContrasena.getText().toString().equals("")) {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextEmail.getText().toString(),
                    editTextContrasena.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        iniciarApp(editTextEmail.getText().toString());

                    } else {

                        Toast toast = Toast.makeText(Login.this, "Usuario o contraseña incorrectos, inténtalo de nuevo!", Toast.LENGTH_SHORT);

                        toast.show();

                    }
                }
            });

        }

        if (editTextEmail.getText().toString().equals("") || editTextContrasena.getText().toString().equals("") ) {

            Toast toast = Toast.makeText(Login.this, "Uno de los campos está vacío!", Toast.LENGTH_SHORT);
            toast.show();

        }




    }

    public void iniciarApp(String email) {

        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.putExtra("email", email);

        startActivity(intent);
        finish();

    }


}