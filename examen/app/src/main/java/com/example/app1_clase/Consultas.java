package com.example.app1_clase;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import BaseDatos.BDEscuela;
import Entidades.Alumno;

@SuppressWarnings("unchecked")
public class Consultas extends Activity {
    ListView lista;
    RecyclerView recicler;
    RecyclerView.Adapter adaper;
    RecyclerView.LayoutManager layoutManager;
    List<Alumno> registros;
    EditText caja;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta);
        caja=findViewById(R.id.ctx_numControlConsulta);
        lista=findViewById(R.id.listview1);

        recicler=findViewById(R.id.rv_alumnosConsulta);

        recicler.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);

        recicler.setLayoutManager(layoutManager);

        // ---------------------
        //         Evento

        caja.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        BDEscuela conexion= BDEscuela.gettAppDatabase(getBaseContext());
                        String t = caja.getText().toString();

                        if(!t.equals("")){
                            registros = conexion.alumnoDAO().buscarPorNumControlFiltrado(t);

                            String datos[] = new String[registros.size()];

                            short cont=0;

                            for (Alumno d:registros) {
                                if (cont==0) {
                                    datos[cont++]="------------------------------\n"+d.toString()+"\n-------------------------------";
                                }else{
                                    datos[cont++]=d.toString()+"\n--------------------------------";
                                }
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adaper=new Registro(datos);
                                    recicler.setAdapter(adaper);
                                }
                            });

                        }
                        else{

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String []a = {};
                                    adaper=new Registro(a);
                                    recicler.setAdapter(adaper);
                                }
                            });

                        }

                    }

                }).start();

            }
        });

        //---------------------

        //---------------------Recycler View
    }
}

class Registro extends RecyclerView.Adapter<Registro.MyViewHolder>{

    private String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(TextView t) {
            super(t);
            textView = t;
        }
    }

    public Registro(String [] mDataset){
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public Registro.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textviewreciclerview,
                parent, false);
        MyViewHolder vh = new MyViewHolder(tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}