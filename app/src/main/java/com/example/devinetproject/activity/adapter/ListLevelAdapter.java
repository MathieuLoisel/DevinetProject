package com.example.devinetproject.activity.adapter;

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
import androidx.lifecycle.Observer;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.SelectLevelActivity;
import com.example.devinetproject.activity.SelectListActivity;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.bo.Word;
import com.example.devinetproject.vm.WordVm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLevelAdapter extends ArrayAdapter<Level> {

    private WordVm wordVm;
    private Map<Integer,Integer> mapLevelProgress;
    private SelectLevelActivity activity;

    public ListLevelAdapter(@NonNull Context context, int resource, @NonNull List<Level> objects, WordVm wordVm, SelectLevelActivity activity) {
        super(context, resource, objects);
        this.wordVm = wordVm;
        mapLevelProgress = new HashMap<>();
        this.activity = activity;
        setObserve(objects);
    }

    void setObserve(final List<Level> objects){
        //Maintenant on réucpère les words par catéogrie (progress)
        for(Level lev: objects){
            wordVm.getByLevel(lev.getId()).observe(activity, new Observer<List<Word>>() {
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
                        mapLevelProgress.putIfAbsent(words.get(0).getIdLevel(),progress);
                        if(mapLevelProgress.size() == objects.size()){
                            ListLevelAdapter.this.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Level levelToDisplay = getItem(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.style_ligne_select_level_layout,parent,false);
        }

        TextView idLevel = convertView.findViewById(R.id.tv_level_select_line);
        idLevel.setText("Niveau " + getItem(position).getId() + " - " + (getItem(position).getId() + 3) + " lettres");

        int progress = 0;
        if (mapLevelProgress.size() > 0 && mapLevelProgress.containsKey(levelToDisplay.getId())){
            progress = mapLevelProgress.get(levelToDisplay.getId());
        }
        TextView tvPourcentage = convertView.findViewById(R.id.tv_level_select_level_progression);
        tvPourcentage.setText(progress + " %");

        ProgressBar progressBar = convertView.findViewById(R.id.stats_progressbar);
        progressBar.setProgress(progress);

        return convertView;
    }
}
