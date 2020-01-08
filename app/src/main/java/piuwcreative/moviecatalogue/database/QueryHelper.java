package piuwcreative.moviecatalogue.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import piuwcreative.moviecatalogue.model.MovieModel;

import static piuwcreative.moviecatalogue.database.DatabaseContract.ID;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.CONTENT_URI_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.TABLE_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.TABLE_TV;

public class QueryHelper {
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;

    private static QueryHelper INSTANCE;

    public QueryHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static QueryHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new QueryHelper(context);
                }
            }
        }

        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAllMovie() {
        return database.query(TABLE_MOVIE,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC");
    }

    public Cursor queryAllTv() {
        return database.query(TABLE_TV,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC");
    }

    public Cursor queryMovieById(String id) {
        return database.query(TABLE_MOVIE, null, ID + " = ?",
                new String[]{id}, null, null, null);
    }

    public Cursor queryTvById(String id) {
        return database.query(TABLE_TV, null, ID + " = ?",
                new String[]{id}, null, null, null);
    }

    public long insertMovie(ContentValues values) {
        return database.insert(TABLE_MOVIE, null, values);
    }
    public long insertTv(ContentValues values) {
        return database.insert(TABLE_TV, null, values);
    }
    public int deleteMovieById(String id) {
        return database.delete(TABLE_MOVIE, ID + " = ?", new String[]{id});
    }

    public int deleteTvById(String id) {
        return database.delete(TABLE_TV, ID + " = ?", new String[]{id});
    }



}
