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
import com.example.devinetproject.bo.Category;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //On décompresse le fichier style_ligne_utilisateur
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View nouvelleLigne = li.inflate(R.layout.style_ligne_select_list_layout,parent,false);

        TextView idCategory = nouvelleLigne.findViewById(R.id.tv_list_select_line);

        idCategory.setText("Liste " + getItem(position).getId());

        //On retourne la ligne
        return nouvelleLigne;
    }
}
