package com.example.app1_clase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    public void abirmenu_Altas(View v){
        Intent i=new Intent(this, Altas.class);
        startActivity(i);
    }
    public void acciones (View v){
        Intent i=null;
        switch (v.getId()){
            case R.id.btn_agregar_Menu:
                i=new Intent(this, Altas.class);
                break;
            case R.id.btn_eliminar_Menu:
                i=new Intent(this, Bajas.class);
                break;
            case R.id.btn_modificar_Menu:
                i=new Intent(this, Modificaciones.class);
                break;
            case R.id.btn_consultas_Menu:
                i=new Intent(this, Consultas.class);
                break;
        }
        startActivity(i);
    }

}
