package com.example.gamespysock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private TextView resultTextView;
    private GameRecommendation gameRecommendation;
    private SharedPreferences sharedPreferences;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferences = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE);
        gameRecommendation = new GameRecommendation(this);
        clearButton = findViewById(R.id.clearButton);

        if (sharedPreferences.contains("recommendedGames")) {
            loadRecommendations();
        } else {
            Intent intent = getIntent();
            String selectedGenre = intent.getStringExtra("selectedGenre");
            boolean selectedTime = intent.getBooleanExtra("selectedTime", false);
            String selectedMultiplayer = intent.getStringExtra("selectedMultiplayer");
            String selectedRating = intent.getStringExtra("selectedRating");

            List<Game> recommendedGames = gameRecommendation.generateRecommendations(selectedGenre, selectedTime, selectedMultiplayer, selectedRating);
            saveRecommendations(recommendedGames);
            displayRecommendations(recommendedGames);
        }
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearRecommendations();
            }
        });
    }

    private void saveRecommendations(List<Game> games) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(games);
        editor.putString("recommendedGames", json);
        editor.apply();
    }

    private void loadRecommendations() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("recommendedGames", null);
        Game[] gamesArray = gson.fromJson(json, Game[].class);
        List<Game> games = Arrays.asList(gamesArray);
        displayRecommendations(games);
    }

    private void displayRecommendations(List<Game> games) {
        gameAdapter = new GameAdapter(this, games);
        recyclerView.setAdapter(gameAdapter);
    }

    private void clearRecommendations() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("recommendedGames");
        editor.apply();
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}