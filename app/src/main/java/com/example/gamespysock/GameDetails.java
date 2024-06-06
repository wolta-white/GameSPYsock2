package com.example.gamespysock;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class GameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView genreTextView = findViewById(R.id.genreTextView);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView graphicsTextView = findViewById(R.id.graphicsTextView);
        TextView multiplayerTextView = findViewById(R.id.multiplayerTextView);
        TextView ratingTextView = findViewById(R.id.ratingTextView);
        TextView yearTextView = findViewById(R.id.yearTextView);
        TextView requirementsTextView = findViewById(R.id.requirementsTextView);
        ImageView posterImageView = findViewById(R.id.posterImageView);

        Game game = getIntent().getParcelableExtra("game");

        if (game != null) {
            titleTextView.setText(game.getTitle());
            descriptionTextView.setText(game.getDescription());
            genreTextView.setText("Жанр: " + game.getGenre());
            timeTextView.setText("Время прохождения: " + game.getTime() + " часов");
            graphicsTextView.setText("Графика: " + game.getGraphics());
            multiplayerTextView.setText("Многопользовательская: " + game.getMultiplayer());
            ratingTextView.setText("Рейтинг: " + game.getRating());
            yearTextView.setText("Год выпуска: " + game.getYear());
            requirementsTextView.setText("Системные требования: " + game.getRequirements());
            Picasso.get().load(game.getImage()).into(posterImageView);
        }
    }
}