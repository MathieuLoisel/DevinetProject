package com.example.devinetproject.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.devinetproject.R;
import com.example.devinetproject.bo.Level;

import java.util.List;

public class LevelAdapter2 extends ArrayAdapter<Level> {

    public LevelAdapter2(@NonNull Context context, int resource, @NonNull List<Level> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //On décompresse le fichier style_ligne_utilisateur
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View nouvelleLigne = li.inflate(R.layout.style_ligne_select_level_layout,parent,false);

        TextView idCategory = nouvelleLigne.findViewById(R.id.tv_level_select_list);

        idCategory.setText("Liste " + getItem(position).getId());

        //On retourne la ligne
        return nouvelleLigne;
    }
}
