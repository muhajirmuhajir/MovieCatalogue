package piuwcreative.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import piuwcreative.moviecatalogue.database.QueryHelper;

import static piuwcreative.moviecatalogue.database.DatabaseContract.AUTHORITY;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.CONTENT_URI_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.TABLE_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.CONTENT_URI_TV;
import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.TABLE_TV;

public class MovieProvider extends ContentProvider {
    private static final int MOVIE_ID = 1;
    private static final int MOVIE = 2;

    private static final int TV_ID = 3;
    private static final int TV = 4;

    private QueryHelper queryHelper;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //movie
        URI_MATCHER.addURI(AUTHORITY, TABLE_MOVIE, MOVIE);
        URI_MATCHER.addURI(AUTHORITY, TABLE_MOVIE + "/#", MOVIE_ID);

        //tv
        URI_MATCHER.addURI(AUTHORITY, TABLE_TV, TV);
        URI_MATCHER.addURI(AUTHORITY, TABLE_TV + "/#", TV_ID);
    }

    public MovieProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;

        switch (URI_MATCHER.match(uri)) {
            case MOVIE_ID:
                deleted = queryHelper.deleteMovieById(uri.getLastPathSegment());
                getContext().getContentResolver().notifyChange(CONTENT_URI_MOVIE, null);
                break;
            case TV_ID:
                deleted = queryHelper.deleteTvById(uri.getLastPathSegment());
                getContext().getContentResolver().notifyChange(CONTENT_URI_TV,null);
                break;
            default:
                deleted = 0;
        }

        return deleted;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;

        switch (URI_MATCHER.match(uri)) {
            case MOVIE:
                added = queryHelper.insertMovie(values);
                getContext().getContentResolver().notifyChange(CONTENT_URI_MOVIE, null);
                return Uri.parse(CONTENT_URI_MOVIE + "/" + added);
            case TV:
                added = queryHelper.insertTv(values);
                getContext().getContentResolver().notifyChange(CONTENT_URI_TV, null);
                return Uri.parse(CONTENT_URI_TV + "/" + added);
            default:
                added = 0;
        }


        return Uri.parse("penambahan data gagal");
    }

    @Override
    public boolean onCreate() {
        queryHelper = QueryHelper.getInstance(getContext());
        queryHelper.open();
        Log.i("checking_bro", "content provider di buka");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case MOVIE:
                cursor = queryHelper.queryAllMovie();
                break;
            case MOVIE_ID:
                cursor = queryHelper.queryMovieById(uri.getLastPathSegment());
                break;
            case TV:
                cursor = queryHelper.queryAllTv();
                break;
            case TV_ID:
                Log.i("checking_bro1", "masuk di per query id");
                cursor = queryHelper.queryTvById(uri.getLastPathSegment());
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
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
