package com.example.devinetproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.devinetproject.R;
import com.example.devinetproject.bo.Word;
import com.example.devinetproject.vm.WordVm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.view.View.generateViewId;

public class PlayActivity extends AppCompatActivity {
    boolean[] isFull;
    public static final int LAST_ITEM_BIN = 1;
    private Word wordToGuess = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.ibtn_next).setClickable(false);

        ConstraintLayout constraintLayout = findViewById(R.id.layout_play);

        List<Word> words = getIntent().getParcelableArrayListExtra("listWords");
        String wordToGuessName = null;
        int compteur = 0;
        for (Word word : words) {
            compteur++;
            wordToGuess = word;
            wordToGuessName = word.getGuessWord();
            if (!word.getGuessWord().toUpperCase().equals(wordToGuess.getProposal().toUpperCase())) {
                break;
            } else {
                if (compteur == words.size()) {
                    finish();
                }
            }
        }
        wordToGuessName = wordToGuessName.toUpperCase();


        StringBuffer userProposal = new StringBuffer();
        if (wordToGuessName.equals(userProposal.toString())) {
            findViewById(R.id.ibtn_next).setClickable(true);
        }

        //Création de tableaux d'id permettant de mettre en place la horizontalChain
        int[] viewIdsTextViewEmpty = new int[wordToGuessName.toCharArray().length + LAST_ITEM_BIN];
        int[] viewIdsTextViewWithChar = new int[wordToGuessName.toCharArray().length];

        //Création d'un tableau de boolean permettant de remplir dynamiquement les TextView vide en fonction des placers qu'il reste
        isFull = new boolean[wordToGuessName.toCharArray().length];
        ImageButton ibtnErase = null;

        List<Character> wordToGuessShuffled = shuffleWordToGuess(wordToGuessName);

        ConstraintSet constraintSetTvEmpty = new ConstraintSet();
        //Création des textViews vides
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
            if (j == wordToGuessShuffled.size() - 1) {
                ibtnErase = createIbtnErase(constraintLayout, viewIdsTextViewEmpty, constraintSetTvEmpty, j, viewIdsTextViewWithChar);
            }
            //Placement des textviews vides en hauteur
            constraintSetTvEmpty.clone(constraintLayout);
            constraintSetTvEmpty.connect(tv.getId(), ConstraintSet.TOP, R.id.iv_photo, ConstraintSet.BOTTOM, 20);
            constraintSetTvEmpty.applyTo(constraintLayout);
        }

        //Création de l'horizontalChain des textviews vides
        constraintSetTvEmpty.clone(constraintLayout);
        constraintSetTvEmpty.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIdsTextViewEmpty, null, ConstraintSet.CHAIN_PACKED);
        constraintSetTvEmpty.applyTo(constraintLayout);

        createTextViewForEachChar(wordToGuessShuffled, constraintLayout, viewIdsTextViewWithChar, viewIdsTextViewEmpty, ibtnErase, userProposal);

        //Contraintes pour les textviews pleins
        ConstraintSet constraintSetTvFull = new ConstraintSet();
        constraintSetTvFull.clone(constraintLayout);
        constraintSetTvFull.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIdsTextViewWithChar, null, ConstraintSet.CHAIN_SPREAD);
        constraintSetTvFull.applyTo(constraintLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_main_about_us:
                Intent intentAboutUs = new Intent(this, AboutUsActivity.class);
                startActivity(intentAboutUs);
                return true;
            case R.id.btn_home:
                Intent intentHome = new Intent(this, MainActivity.class);
                startActivity(intentHome);
                return true;
            case R.id.btn_main_settings:
                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ImageButton createIbtnErase(ConstraintLayout constraintLayout, final int[] viewIdsTextViewEmpty, ConstraintSet constraintSetTvEmpty, int j, final int[] viewIdsTextViewWithChar) {
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
                for (int i = 0; i < viewIdsTextViewEmpty.length - LAST_ITEM_BIN; i++) {
                    TextView tvEmpty = findViewById(viewIdsTextViewEmpty[i]);
                    tvEmpty.setText("");
                    TextView tvFull = findViewById(viewIdsTextViewWithChar[i]);
                    tvFull.setVisibility(View.VISIBLE);
                    isFull[i] = false;
                }
            }
        });
        viewIdsTextViewEmpty[j + LAST_ITEM_BIN] = ibtnErase.getId();
        constraintLayout.addView(ibtnErase);
        constraintSetTvEmpty.clone(constraintLayout);
        constraintSetTvEmpty.connect(ibtnErase.getId(), ConstraintSet.TOP, R.id.iv_photo, ConstraintSet.BOTTOM, 5);
        constraintSetTvEmpty.applyTo(constraintLayout);
        return ibtnErase;
    }

    /**
     * Fonction permettant de créer automatiquement les textview pour chaque caractère du mot à deviner.
     * Attribut à chaque TextView les mêmes caractéristiques
     *
     * @param wordToGuessShuffled     Liste de caractères mélangés du mot à deviner mélangé
     * @param constraintLayout        Layout sur lequel on va appliquer les différentes contraintes
     * @param viewIdsTextViewWithChar Tableau d'id (int) que l'on rempli avec les ids de chaque textview créé. Utiliser pour le horizontalChain
     * @param ibtnErase               Bouton auquel on se réfère pour placer les différents TextView en hauteur
     */
    private void createTextViewForEachChar(List<Character> wordToGuessShuffled,
                                           ConstraintLayout constraintLayout,
                                           final int[] viewIdsTextViewWithChar,
                                           final int[] viewIdsTextViewEmpty,
                                           ImageButton ibtnErase,
                                           final StringBuffer userProposal) {
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
                    for (int j = 0; j < isFull.length; j++) {
                        if (!isFull[j]) {
                            final TextView tvVides = findViewById(viewIdsTextViewEmpty[j]);
                            tvVides.setText(tv.getText());
                            isFull[j] = true;
                            final int finalJ = j;
                            tvVides.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    tv.setVisibility(View.VISIBLE);
                                    tvVides.setText("");
                                    isFull[finalJ] = false;
                                }
                            });
                            break;
                        }
                    }
                    for (int k = 0; k < isFull.length; k++) {
                        if (k == isFull.length - 1) {
                            if (isFull[k]) {
                                for (int m = 0; m < viewIdsTextViewEmpty.length - LAST_ITEM_BIN; m++) {
                                    TextView tv = findViewById(viewIdsTextViewEmpty[m]);
                                    userProposal.append(tv.getText());
                                }
                            }
                        }
                    }
                    wordToGuess.setProposal(userProposal.toString().toUpperCase());
                    if (isFull[isFull.length - 1]) {
                        WordVm wordVm = new ViewModelProvider(PlayActivity.this).get(WordVm.class);
                        wordVm.update(wordToGuess);

                        if (userProposal.toString().equals(wordToGuess.getGuessWord().toUpperCase())) {
                            findViewById(R.id.ibtn_next).setClickable(true);
                            new AlertDialog.Builder(PlayActivity.this)
                                    .setTitle("Bravo ! Vous avez deviné le mot !")
                                    .setNeutralButton("OK", null)
                                    .setIcon(android.R.drawable.star_big_on)
                                    .show();
                        }
                    }
                }
            });
            viewIdsTextViewWithChar[i] = tv.getId();
            constraintLayout.addView(tv);
            ConstraintSet constraintSetTV = new ConstraintSet();
            constraintSetTV.clone(constraintLayout);
            assert ibtnErase != null;
            constraintSetTV.connect(tv.getId(), ConstraintSet.TOP, ibtnErase.getId(), ConstraintSet.BOTTOM);
            constraintSetTV.applyTo(constraintLayout);
        }
    }

    /**
     * Fonction permettant de mélanger le mot à deviner
     *
     * @param wordToGuess Mot à deviner tel qu'il est en BDD
     * @return Une liste de caractères correspondant aux caractères du mot à deviner mélangé
     */
    private List<Character> shuffleWordToGuess(String wordToGuess) {
        Character[] wordToGuessCharacter = new Character[wordToGuess.toCharArray().length];
        for (int i = 0; i < wordToGuess.toCharArray().length; i++) {
            wordToGuessCharacter[i] = wordToGuess.toCharArray()[i];
        }

        List<Character> wordToGuessArray = Arrays.asList(wordToGuessCharacter);
        Collections.shuffle(wordToGuessArray);
        return wordToGuessArray;
    }

    public void onClickNextWord(View view) {
        PlayActivity.this.recreate();
    }
}