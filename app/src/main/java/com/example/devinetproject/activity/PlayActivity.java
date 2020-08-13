package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.devinetproject.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.view.View.generateViewId;

public class PlayActivity extends AppCompatActivity {
    boolean[] isFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ConstraintLayout constraintLayout = findViewById(R.id.layout_play);

        //TODO:Récupérer le mot à deviner
        String wordToGuess = "Orthodox";

        //Création de tableaux d'id permettant de mettre en place la horizontalChain
        int[] viewIdsTextViewEmpty = new int[wordToGuess.toCharArray().length+1];
        int[] viewIdsTextViewWithChar = new int[wordToGuess.toCharArray().length];

        //Création d'un tableau de boolean permettant de remplir dynamiquement les TextView vide en fonction des placers qu'il reste
        isFull = new boolean[wordToGuess.toCharArray().length];
        ImageButton ibtnErase = null;

        List<Character> wordToGuessShuffled = shuffleWordToGuess(wordToGuess);

        ConstraintSet constraintSetTvEmpty = new ConstraintSet();
        for (int j = 0; j < wordToGuessShuffled.size(); j++) {
            TextView tv = new TextView(this);
            tv.setId(generateViewId());
            Log.i("id", String.valueOf(tv.getId()));
            tv.setWidth(90);
            tv.setHeight(90);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.border_black);
            viewIdsTextViewEmpty[j] = tv.getId();
            constraintLayout.addView(tv);
            if (j == wordToGuessShuffled.size()-1){
                ibtnErase = createIbtnErase(constraintLayout, viewIdsTextViewEmpty, constraintSetTvEmpty, j);
            }
            //Placement des textviews vide en hauteur
            constraintSetTvEmpty.clone(constraintLayout);
            constraintSetTvEmpty.connect(tv.getId(),ConstraintSet.TOP,R.id.iv_photo,ConstraintSet.BOTTOM, 20);
            constraintSetTvEmpty.applyTo(constraintLayout);
        }

        //Création de l'horizontalChain des textviews vides
        constraintSetTvEmpty.clone(constraintLayout);
        constraintSetTvEmpty.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIdsTextViewEmpty, null, ConstraintSet.CHAIN_PACKED);
        constraintSetTvEmpty.applyTo(constraintLayout);

        createTextViewForEachChar(wordToGuessShuffled, constraintLayout, viewIdsTextViewWithChar, viewIdsTextViewEmpty, ibtnErase);

        //Contraintes pour les textviews pleins
        ConstraintSet constraintSetTvFull = new ConstraintSet();
        constraintSetTvFull.clone(constraintLayout);
        constraintSetTvFull.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIdsTextViewWithChar, null, ConstraintSet.CHAIN_SPREAD);
        constraintSetTvFull.applyTo(constraintLayout);
    }




    private ImageButton createIbtnErase(ConstraintLayout constraintLayout, final int[] viewIdsTextViewEmpty, ConstraintSet constraintSetTvEmpty, int j) {
        ImageButton ibtnErase;
        ibtnErase = new ImageButton(this);
        ibtnErase.setId(generateViewId());
        ibtnErase.setMinimumWidth(120);
        ibtnErase.setMaxWidth(120);
        ibtnErase.setMinimumHeight(120);
        ibtnErase.setMaxHeight(120);
        ibtnErase.setImageResource(R.drawable.ic_baseline_delete_24);
        ibtnErase.setOnClickListener(new View.OnClickListener() {
            //TODO:OnClick pour le bouton erase. viewIdsTextViewEmpty setText(""). viewIdsTextViewFull setVisibility(VISIBLE)
            @Override
            public void onClick(View view) {
                for (int i = 0; i < viewIdsTextViewEmpty.length; i++) {
                    findViewById(viewIdsTextViewEmpty[i]);

                }
            }
        });
        viewIdsTextViewEmpty[j+1] = ibtnErase.getId();
        constraintLayout.addView(ibtnErase);
        constraintSetTvEmpty.clone(constraintLayout);
        constraintSetTvEmpty.connect(ibtnErase.getId(),ConstraintSet.TOP,R.id.iv_photo,ConstraintSet.BOTTOM, 5);
        constraintSetTvEmpty.applyTo(constraintLayout);
        return ibtnErase;
    }

    /**
     * Fonction permettant de créer automatiquement les textview pour chaque caractère du mot à deviner.
     * Attribut à chaque TextView les mêmes caractéristiques
     *
     * @param wordToGuessShuffled Liste de caractères mélangés du mot à deviner mélangé
     * @param constraintLayout Layout sur lequel on va appliquer les différentes contraintes
     * @param viewIdsTextViewWithChar Tableau d'id (int) que l'on rempli avec les ids de chaque textview créé. Utiliser pour le horizontalChain
     * @param ibtnErase Bouton auquel on se réfère pour placer les différents TextView en hauteur
     */
    private void createTextViewForEachChar(List<Character> wordToGuessShuffled, ConstraintLayout constraintLayout, int[] viewIdsTextViewWithChar, final int[] viewIdsTextViewEmpty, ImageButton ibtnErase) {
        for (int i = 0; i < wordToGuessShuffled.size(); i++) {
            final TextView tv = new TextView(this);
            tv.setId(generateViewId());
            tv.setText(String.valueOf(wordToGuessShuffled.get(i)));
            tv.setWidth(90);
            tv.setHeight(90);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.border_black);
            //Création d'onCLickListeners pour remplir automatiquement les cases vides du mot à deviner lorsque l'on clique sur une lettre
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv.setVisibility(View.INVISIBLE);
                    //TODO:faire une boucle sur un tableau de boolean de la taille du mot. Au premier isVide == true, on set le texte et on passe isVide à false
                    for (int j = 0; j < isFull.length; j++) {
                        if (!isFull[j]){
                            final TextView textview = findViewById(viewIdsTextViewEmpty[j]);
                            textview.setText(tv.getText());
                            isFull[j] = true;
                            final int finalJ = j;
                            textview.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    tv.setVisibility(View.VISIBLE);
                                    textview.setText("");
                                    isFull[finalJ] = false;
                                }
                            });
                            break;
                        }
                    }
                }
            });
            viewIdsTextViewWithChar[i] = tv.getId();
            constraintLayout.addView(tv);
            ConstraintSet constraintSetTV = new ConstraintSet();
            constraintSetTV.clone(constraintLayout);
            assert ibtnErase != null;
            constraintSetTV.connect(tv.getId(),ConstraintSet.TOP,ibtnErase.getId(),ConstraintSet.BOTTOM);
            constraintSetTV.applyTo(constraintLayout);
        }
    }

    /**
     * Fonction permettant de mélanger le mot à deviner
     * @param wordToGuess Mot à deviner tel qu'il est en BDD
     * @return Une liste de caractères correspondant aux caractères du mot à deviner mélangé
     */
    private List<Character> shuffleWordToGuess(String wordToGuess) {
        Character[] wordToGuessCharacter = new Character[wordToGuess.toCharArray().length];
        for (int i = 0; i < wordToGuess.toCharArray().length; i++) {
            wordToGuessCharacter[i]  = wordToGuess.toCharArray()[i];
        }

        List<Character> wordToGuessArray = Arrays.asList(wordToGuessCharacter);
        Collections.shuffle(wordToGuessArray);
        return wordToGuessArray;
    }
}