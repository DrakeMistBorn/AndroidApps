package com.alivelife;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView miRecView;
    private ArrayList<Alumno> datos;
    private AdaptadorAlumnos adaptador;
    private int posicion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargaAdaptador();
        fijaAdaptador();
        registerForContextMenu(miRecView);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle("Operaciones");
        inflater.inflate(R.menu.menu_context_recview, menu);


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Snackbar.make(miRecView,"la posicion es " + posicion,Snackbar.LENGTH_LONG).show();
        switch (item.getItemId()) {
            case R.id.borrar:
                Snackbar.make(miRecView,"la posicion es " + posicion,Snackbar.LENGTH_LONG).show();
                //borrarElemento(posicion);
                return true;
            case R.id.nuevo:
                //nuevoElemento();
                return true;
            case R.id.modificar:
                return true;

            default:
                return super.onContextItemSelected(item);
        }




    }

    private void cargaAdaptador() {
        datos = new ArrayList();
//int imagenEst, String nombre, String matricula,String grupo,String anio, int imagenFoto
        datos.add(new Alumno(android.R.drawable.star_off, "EDUARDO JAVIER ACUñA", "MM001", "android", "2015", false, R.drawable.avatar));
        datos.add(new Alumno(android.R.drawable.star_off, "SERGIO ALCALDE", "MM002", "android", "2015", false, R.drawable.avatar));
        //Inicializamos el RecyclerView
        miRecView = (RecyclerView) findViewById(R.id.reciclador);

        adaptador = new AdaptadorAlumnos(datos);
    }

    private void fijaAdaptador() {

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aqui procesamos el evento delegado por la vista en el AdaptadorAlumnos
                posicion= miRecView.getChildAdapterPosition(v);//devuelve la posision pulsada
                Snackbar.make(miRecView,"la posicion es " + posicion,Snackbar.LENGTH_LONG).show();
                openContextMenu(miRecView); // abrimos el menu contextual

            }
        });
        miRecView.setAdapter(adaptador);
        //fijamos el layoutManager
        //miRecView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        miRecView.setLayoutManager(new GridLayoutManager(this, 1));
        //usamos un addItemDecoration para añadir una línea de separación entre cada elemento del RecyclerView
        miRecView.addItemDecoration( new ItemDecorationSeparador(this, ItemDecorationSeparador.VERTICAL_LIST));
        // miRecView.addItemDecoration(
        //       new ItemDecorationSeparador(this,ItemDecorationSeparador.HORIZONTAL_LIST));
        //fijamos la animación por defecto
        miRecView.setItemAnimator(new DefaultItemAnimator());
    }
    /*private void borrarElemento(int pos){
        datos.remove(pos);
        adaptador.notifyItemRemoved(pos);
        Snackbar.make(miRecView," borrado el: " + pos,Snackbar.LENGTH_LONG).show();
    }*/

    /*private void nuevoElemento(){
        Snackbar.make(miRecView,"la posicion es " + adaptador.getItemCount(),Snackbar.LENGTH_LONG).show();
        datos.add(posicion,new Alumno(android.R.drawable.star_off, "MIGUEL DIAZ", "MM007", "android", "2016", false, R.drawable.imagen_8));
        Snackbar.make(miRecView,"la posicion es " + adaptador.getItemCount(),Snackbar.LENGTH_LONG).show();
        adaptador.notifyItemInserted(posicion);
    }*/



}



