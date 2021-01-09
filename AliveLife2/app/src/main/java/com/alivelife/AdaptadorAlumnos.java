package com.alivelife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by MATRIX on 31/10/16.
 */

public class AdaptadorAlumnos extends RecyclerView.Adapter<com.alivelife.AdaptadorAlumnos.AlumnosViewHolder>implements View.OnClickListener {
    private View.OnClickListener listener;
    private ArrayList<Alumno> datos;

 public AdaptadorAlumnos(ArrayList<Alumno> datos){

     this.datos=datos;
 }

    @Override
    public AlumnosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflamos el layout para representar los elementos
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_4,parent,false);
        //creamos un objeto AlumnosViewHolder para obtener las referencias a las vistas del layout y le pasamos la vista inflada con el layout
        AlumnosViewHolder avh= new AlumnosViewHolder(itemView);
        // fijamos el evento en la vista del elemento que acabamos de construir
        itemView.setOnClickListener(this);
        return avh;
    }

    @Override
    public void onBindViewHolder(AlumnosViewHolder holder, int pos) {
       //obtenemos los datos a mostrar en esa posición
       Alumno item= datos.get(pos);
       // se los pasamos al método bindAlumnos del viewHolder para que los asigne
       holder.bindAlumnos(item);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    // codificamos el método del interfaz View.OnClickListener y le asignamos el listener de la actividad que contiene al RecyclerView
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    // sobreescribimos el método onClick del evento y lo delegamos  al listener de la actividad que contiene el RecyclerView para que lo procese
    @Override
    public void onClick(View view) {
        if(listener != null)// si no es nulo lanzamos el metodo onClick de la actividad que contiene el RecyclerView
            listener.onClick(view);
    }

    public static class AlumnosViewHolder extends RecyclerView.ViewHolder{

      private ImageView foto;
      private ImageView estrella;
      private TextView nombre;
      private TextView matricula;
      private TextView grupo;
      private TextView anio;
      public AlumnosViewHolder (View miVista){
          super(miVista);
          foto = (ImageView) miVista.findViewById(R.id.foto);
          estrella = (ImageView) miVista.findViewById(R.id.estrella);
          nombre = (TextView) miVista.findViewById(R.id.nombre);
          matricula = (TextView) miVista.findViewById(R.id.matricula);
          grupo = (TextView) miVista.findViewById(R.id.grupo);
          anio = (TextView) miVista.findViewById(R.id.anio);

      }
       public void bindAlumnos(Alumno a){
           foto.setImageResource(a.getImagenFoto());
           nombre.setText(a.getNombre());
           matricula.setText(a.getMatricula());
           grupo.setText(a.getGrupo());
           anio.setText(a.getAnio());
           if(a.getEstrella()){
               estrella.setImageResource(android.R.drawable.star_on);
           } else {
               estrella.setImageResource(android.R.drawable.star_off);
           }

       }
    }
}
