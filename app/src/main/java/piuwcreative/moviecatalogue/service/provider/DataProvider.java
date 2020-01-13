package piuwcreative.moviecatalogue.service.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;

import piuwcreative.moviecatalogue.data.database.AppDatabase;

public class DataProvider extends ContentProvider {
    public static final String AUTHORITY = "piuwcreative.moviecatalogue";
    public static final String TABLE_MOVIE = "movie_table";
    public static final String TABLE_TV = "tv_table";

    private static final int MOVIE_ID = 1;
    private static final int MOVIE = 2;

    private static final int TV_ID = 3;
    private static final int TV = 4;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private AppDatabase database;

    static {
        //movie
        URI_MATCHER.addURI(AUTHORITY, TABLE_MOVIE, MOVIE);
        URI_MATCHER.addURI(AUTHORITY, TABLE_MOVIE + "/#", MOVIE_ID);

        //tv
        URI_MATCHER.addURI(AUTHORITY, TABLE_TV, TV);
        URI_MATCHER.addURI(AUTHORITY, TABLE_TV + "/#", TV_ID);
    }

    public DataProvider() {
    }

    @Override
    public boolean onCreate() {
        database = AppDatabase.getInstance(getContext());
        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return uri;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case MOVIE:
                cursor = database.movieDao().getAllMovieProvider();
                break;
            case MOVIE_ID:
                cursor = null;
               // cursor = queryHelper.queryMovieById(uri.getLastPathSegment());
                break;
            case TV:
                cursor = database.movieDao().getAllTvProvider();
                break;
            case TV_ID:
                cursor = null;
                //cursor = queryHelper.queryTvById(uri.getLastPathSegment());
                break;
            default:
                Log.i("checking_bro1", "masuk di default");
                cursor = null;
        }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return 0;
    }
}
