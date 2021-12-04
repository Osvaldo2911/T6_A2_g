package com.example.app1_clase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import BaseDatos.BDEscuela;

public class Bajas extends AppCompatActivity {

    EditText txt_baja_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baja);
        txt_baja_id = findViewById(R.id.ctx_numControlBajas);
    }

    public void baja(View v){

        new Thread(new Runnable() {
            @Override
            public void run() {

                BDEscuela conexionBD= BDEscuela.gettAppDatabase(getBaseContext());
                conexionBD.alumnoDAO().eliminarPorNumControl(txt_baja_id.getText().toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(),"Eliminado con exito",Toast.LENGTH_LONG).show();

                    }
                });
            }

        }).start();

    }

}
