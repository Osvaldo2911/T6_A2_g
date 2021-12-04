package com.example.app1_clase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import BaseDatos.BDEscuela;
import Entidades.Alumno;

public class Altas extends AppCompatActivity {

    EditText txt1, txt2, txt3, txt4, txt5, txt6, txt7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);
        txt1 = findViewById(R.id.ctx_numControl);
        txt2 = findViewById(R.id.ctx_nombre);
        txt3 = findViewById(R.id.ctx_pa);
        txt4 = findViewById(R.id.ctx_sa);
        txt5 = findViewById(R.id.ctx_edad);
        txt6 = findViewById(R.id.ctx_semestre);
        txt7 = findViewById(R.id.ctx_carrera);
    }
    public void agregarAlumno(View v){

        BDEscuela conexionBD= BDEscuela.gettAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {

                conexionBD.alumnoDAO().insertarAlumno(
                        new Alumno(txt1.getText().toString(),
                                   txt2.getText().toString(),
                                   txt3.getText().toString(),
                                   txt4.getText().toString(),
                                   Byte.parseByte(txt5.getText().toString()),
                                   Byte.parseByte(txt6.getText().toString()),
                                   txt7.getText().toString())
                );

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(),"Agregado con exito",Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).start();


    }
}
