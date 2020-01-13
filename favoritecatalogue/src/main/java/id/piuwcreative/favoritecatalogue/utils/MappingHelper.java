package id.piuwcreative.favoritecatalogue.utils;

import android.database.Cursor;

import java.util.ArrayList;

import id.piuwcreative.favoritecatalogue.model.MovieModel;
import id.piuwcreative.favoritecatalogue.model.TvModel;


public class MappingHelper {
    public static ArrayList<MovieModel> cursorToMovies(Cursor cursor) {
        ArrayList<MovieModel> models = new ArrayList<>();
        while (cursor.moveToNext()) {
            MovieModel movie = new MovieModel();
            movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
            movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow("poster_path")));
            movie.setBackdrop(cursor.getString(cursor.getColumnIndexOrThrow("backdrop_path")));
            movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow("overview")));
            movie.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow("vote_average")));
            movie.setReleseDate(cursor.getString(cursor.getColumnIndexOrThrow("release_date")));
            movie.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow("vote_count")));
            models.add(movie);
        }
        cursor.close();
        return models;
    }

    public static ArrayList<TvModel> cursorToTv(Cursor cursor) {
        ArrayList<TvModel> models = new ArrayList<>();
        while (cursor.moveToNext()) {
            TvModel tvModel = new TvModel(cursor);
            models.add(tvModel);
        }
        cursor.close();
        return models;
    }
}
