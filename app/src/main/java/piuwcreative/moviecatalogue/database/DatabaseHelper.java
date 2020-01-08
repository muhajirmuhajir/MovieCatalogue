package piuwcreative.moviecatalogue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static piuwcreative.moviecatalogue.database.DatabaseContract.ID;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.DESCRIPTION;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.GENRES;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.POSTER;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.RATING;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.TABLE_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.TITLE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.YEAR;
import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.TABLE_TV;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmovieapp";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL);",
            TABLE_MOVIE,
            ID,
            TITLE,
            DESCRIPTION,
            YEAR,
            RATING,
            GENRES,
            POSTER
    );

    private static final String SQL_CREATE_TABLE_TV = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL);",
            TABLE_TV,
            ID,
            TITLE,
            DESCRIPTION,
            YEAR,
            RATING,
            GENRES,
            POSTER
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TV);
        onCreate(sqLiteDatabase);
    }
}
