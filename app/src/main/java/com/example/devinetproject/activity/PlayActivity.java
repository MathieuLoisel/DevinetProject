package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.devinetproject.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static android.view.View.generateViewId;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ConstraintLayout constraintLayout = findViewById(R.id.layout_play);

        TextView tv2 = new TextView(this);
        tv2.setId(generateViewId());
        tv2.setText("test");
        constraintLayout.addView(tv2);
        ConstraintSet constraintSetTV2 = new ConstraintSet();
        constraintSetTV2.clone(constraintLayout);
        constraintSetTV2.setMargin(tv2.getId(), ConstraintSet.START, 100);
        constraintSetTV2.connect(tv2.getId(),ConstraintSet.TOP,R.id.iv_photo,ConstraintSet.BOTTOM);
        constraintSetTV2.applyTo(constraintLayout);

        //TODO:Récupérer le mot à deviner
        String wordToGuess = "Orthodoxe";

        int[] viewIds = new int[wordToGuess.toCharArray().length];
        List<TextView> textViews = new ArrayList<>();

        List<Character> wordToGuessShuffled = shuffleWordToGuess(wordToGuess);
        createTextViewForEachChar(wordToGuessShuffled, textViews);

        for (int i = 0; i < textViews.size(); i++) {
            TextView tv = textViews.get(i);
            tv.setId(generateViewId());
            viewIds[i] = tv.getId();
            constraintLayout.addView(tv);

            ConstraintSet constraintSetTV = new ConstraintSet();
            constraintSetTV.clone(constraintLayout);
            constraintSetTV.connect(tv.getId(),ConstraintSet.TOP,R.id.ibtn_erase,ConstraintSet.BOTTOM);
            constraintSetTV.applyTo(constraintLayout);
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIds, null, ConstraintSet.CHAIN_SPREAD);
        constraintSet.applyTo(constraintLayout);
    }

    private void createTextViewForEachChar(List<Character> wordToGuessShuffled, List<TextView> textViews) {
        for (int i = 0; i < wordToGuessShuffled.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(wordToGuessShuffled.get(i)));
            textViews.add(tv);
        }
    }

    private List<Character> shuffleWordToGuess(String wordToGuess) {
        Character[] wordToGuessCharacter = new Character[wordToGuess.toCharArray().length];
        for (int i = 0; i < wordToGuess.toCharArray().length; i++) {
            wordToGuessCharacter[i]  = wordToGuess.toCharArray()[i];
        }

        List<Character> wordToGuessArray = Arrays.asList(wordToGuessCharacter);
        Collections.shuffle(wordToGuessArray);
        return wordToGuessArray;
    }

    public void onClickErase(View view) {
    }
}