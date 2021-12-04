package com.example.app1_clase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import BaseDatos.BDEscuela;

public class Modificaciones extends AppCompatActivity {

    EditText txt, txt8, txt9, txt10, txt11, txt12, txt13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificacion);
        txt9 = findViewById(R.id.txt9);
        txt13= findViewById(R.id.txt13);
        txt12 = findViewById(R.id.txt12);
        txt11 = findViewById(R.id.txt11);
        txt10 = findViewById(R.id.txt10);
        txt8 = findViewById(R.id.txt8);
        txt = findViewById(R.id.txt);
    }

    public void actualizarAlumno(View v){

        new Thread(new Runnable() {
            @Override
            public void run() {

                BDEscuela conexion= BDEscuela.gettAppDatabase(getBaseContext());
                conexion.alumnoDAO().actualizarAlumno(
                        txt9.getText().toString(),
                        txt13.getText().toString(),
                        txt12.getText().toString(),
                        txt11.getText().toString(),
                        Byte.parseByte(txt10.getText().toString()),
                        Byte.parseByte(txt8.getText().toString()),
                        txt.getText().toString()
                );

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(),"Exito",Toast.LENGTH_LONG).show();
                    }
                });

            }
        }).start();

    }

}
