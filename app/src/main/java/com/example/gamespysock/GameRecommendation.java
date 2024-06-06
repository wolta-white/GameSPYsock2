package com.example.gamespysock;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameRecommendation {
    private List<Game> games;

    public GameRecommendation(Context context) {
        this.games = loadGames(context);
    }


    public List<Game> generateRecommendations(String genre, boolean time, String multiplayer, String rating) {
        List<Game> filteredGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getGenre().equals(genre)){
                if(rating.equals("Да")){
                    if (time){
                        if(game.getTime() > 30){
                            filteredGames.add(game);
                        }
                    }else{
                        if(game.getTime() < 30){
                            filteredGames.add(game);
                        }
                    }
                }else {
                    if(!game.getRating().equals("18+")){
                        if (time){
                            if(game.getTime() > 30){
                                filteredGames.add(game);
                            }
                        }else{
                            if(game.getTime() < 30){
                                filteredGames.add(game);
                            }
                        }
                    }
                }
            }
        }
        Collections.shuffle(filteredGames);
        return filteredGames.size() > 5 ? filteredGames.subList(0, 5) : filteredGames;
    }

    private List<Game> loadGames(Context context) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.games);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Type gameListType = new TypeToken<List<Game>>(){}.getType();
        return gson.fromJson(json, gameListType);
    }
}
