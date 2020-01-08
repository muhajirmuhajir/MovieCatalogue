package piuwcreative.moviecatalogue.helper;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.model.MovieModel;

import static piuwcreative.moviecatalogue.database.DatabaseContract.ID;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.DESCRIPTION;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.GENRES;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.POSTER;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.RATING;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.TITLE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.YEAR;

public class MappingHelper {
    public static ContentValues mapObjectToContentValues(MovieModel model) {
        ContentValues values = new ContentValues();
        values.put(ID, model.getId());
        values.put(TITLE, model.getTitle());
        values.put(DESCRIPTION, model.getDescription());
        values.put(YEAR, model.getYear());
        values.put(RATING, model.getRating());
        values.put(GENRES, model.getGenres());
        values.put(POSTER, model.getPoster());

        return values;
    }

    public static ArrayList<MovieModel> mapCursorToArrayList(Cursor cursor) {
        ArrayList<MovieModel> arrayListMovie = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(YEAR));
            String rating = cursor.getString(cursor.getColumnIndexOrThrow(RATING));
            String genres = cursor.getString(cursor.getColumnIndexOrThrow(GENRES));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(POSTER));

            MovieModel model = new MovieModel();
            model.setId(id);
            model.setDescription(description);
            model.setTitle(title);
            model.setYear(year);
            model.setRating(rating);
            model.setGenres(genres);
            model.setPoster(poster);

            arrayListMovie.add(model);
        }

       cursor.close();

        return arrayListMovie;
    }
}
