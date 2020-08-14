package com.example.devinetproject.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.SelectListActivity;
import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.Word;
import com.example.devinetproject.vm.WordVm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private WordVm wordVm;
    private Map<Integer,Integer> mapCategoryProgress;
    private SelectListActivity activity;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects, WordVm wordVm, SelectListActivity activity) {
        super(context, resource, objects);
        this.wordVm = wordVm;
        mapCategoryProgress = new HashMap<>();
        this.activity = activity;
        setObserve(objects);
    }

    void setObserve(final List<Category> objects){
        //Maintenant on réucpère les words par catéogrie (progress)
        for(Category cat: objects){
            wordVm.getByCategory(cat.getId()).observe(activity, new Observer<List<Word>>() {
                @Override
                public void onChanged(List<Word> words) {
                    int numberWords = words.size();
                    int numberWordsFound = 0;
                    for (Word word: words) {
                        if (word.getGuessWord().toUpperCase().equals(word.getProposal().toUpperCase())){
                            numberWordsFound++;
                        }
                    }
                    if (numberWords != 0){
                        int progress = (int)Math.ceil(((double)numberWordsFound/(double)numberWords)*100);
                        mapCategoryProgress.putIfAbsent(words.get(0).getIdCategory(),progress);
                        if(mapCategoryProgress.size() == objects.size()){
                            CategoryAdapter.this.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //On décompresse le fichier style_ligne_utilisateur
        Category categoryToDisplay = getItem(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.style_ligne_select_list_layout,parent,false);
        }

        TextView idCategory = convertView.findViewById(R.id.tv_list_select_line);
        idCategory.setText(categoryToDisplay.getName());
        Log.i("test", String.valueOf(categoryToDisplay.getId()));
        int progress = 0;
        if (mapCategoryProgress.size() > 0 && mapCategoryProgress.containsKey(categoryToDisplay.getId())){
            progress = mapCategoryProgress.get(categoryToDisplay.getId());
        }
        TextView tvPourcentage = convertView.findViewById(R.id.tv_level_select_list_progression);
        tvPourcentage.setText(progress + " %");

        ProgressBar progressBar = convertView.findViewById(R.id.stats_progressbar);
        progressBar.setProgress(progress);

        return convertView;
    }
}
