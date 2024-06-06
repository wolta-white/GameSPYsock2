package com.example.gamespysock;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup genreGroup, timeGroup, multiplayerGroup, ratingGroup;
    private Button submitButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genreGroup = findViewById(R.id.genreGroup);
        timeGroup = findViewById(R.id.timeGroup);
        ratingGroup = findViewById(R.id.ratingGroup);
        submitButton = findViewById(R.id.submitButton);
        sharedPreferences = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("recommendedGames")) {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        } else {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (allOptionsSelected()) {
                        int selectedGenre = genreGroup.getCheckedRadioButtonId();
                        int selectedTime = timeGroup.getCheckedRadioButtonId();
                        int selectedRating = ratingGroup.getCheckedRadioButtonId();

                        String genre = ((RadioButton) findViewById(selectedGenre)).getText().toString();
                        String time = ((RadioButton) findViewById(selectedTime)).getText().toString();
                        String rating = ((RadioButton) findViewById(selectedRating)).getText().toString();
                        boolean upTo30;
                        if(time.equals("До 30 часов")){
                            upTo30 = false;
                        }else {
                            upTo30 = true;
                        }

                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("selectedGenre", genre);
                        intent.putExtra("selectedTime", upTo30);
                        intent.putExtra("selectedRating", rating);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Пожалуйста, выберите все параметры.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
        private boolean allOptionsSelected(){
            return genreGroup.getCheckedRadioButtonId() != -1 &&
                    timeGroup.getCheckedRadioButtonId() != -1 &&
                    ratingGroup.getCheckedRadioButtonId() != -1;
        }
}
