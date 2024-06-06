package com.example.gamespysock;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private String title;
    private String genre;
    private int time;
    private String graphics;
    private String multiplayer;
    private String rating;
    private String image;
    private int year;
    private String description;
    private String requirements;

    public Game() {
    }

    protected Game(Parcel in) {
        title = in.readString();
        genre = in.readString();
        time = in.readInt();
        graphics = in.readString();
        multiplayer = in.readString();
        rating = in.readString();
        image = in.readString();
        year = in.readInt();
        description = in.readString();
        requirements = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeInt(time);
        dest.writeString(graphics);
        dest.writeString(multiplayer);
        dest.writeString(rating);
        dest.writeString(image);
        dest.writeInt(year);
        dest.writeString(description);
        dest.writeString(requirements);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getTime() {
        return time;
    }

    public String getGraphics() {
        return graphics;
    }

    public String getMultiplayer() {
        return multiplayer;
    }

    public String getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }
}