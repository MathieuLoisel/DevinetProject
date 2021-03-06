package com.example.devinetproject.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.devinetproject.R;
import com.example.devinetproject.bo.Level;

import java.util.List;
import java.util.Objects;

public class GeneralLevelAdapter extends ArrayAdapter<Level> {

    public GeneralLevelAdapter(@NonNull Context context, int resource, @NonNull List<Level> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.style_list_results_general, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        tvTitle.setText(Objects.requireNonNull(getItem(position)).getName());

        return convertView;
    }
}