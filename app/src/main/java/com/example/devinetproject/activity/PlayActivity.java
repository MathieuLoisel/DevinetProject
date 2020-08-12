package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.devinetproject.R;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //TODO: Gérer les cas pair et impair et le nombre de lettres

        TextView lettre1 = new TextView(this);
        lettre1.setText("Bonjour");
        lettre1.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));
        ConstraintLayout layout = findViewById(R.id.layout_play);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        constraintSet.connect(lettre1.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 18);
        constraintSet.connect(lettre1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 18);

        constraintSet.applyTo(layout);


        layout.addView(lettre1);


        TextView lettre2 = new TextView(this);
        TextView lettre3 = new TextView(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}