package piuwcreative.moviecatalogue.service;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import piuwcreative.moviecatalogue.helper.MappingHelper;
import piuwcreative.moviecatalogue.interfaces.LoadMovieCallback;
import piuwcreative.moviecatalogue.model.MovieModel;

import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.CONTENT_URI_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.CONTENT_URI_TV;

public class LoadMovieAsync extends AsyncTask<String, Void, ArrayList<MovieModel>> {
    private final WeakReference<Context> weakContext;
    private final WeakReference<LoadMovieCallback> weakCallback;

    public LoadMovieAsync(Context context, LoadMovieCallback callback) {
        weakContext = new WeakReference<>(context);
        weakCallback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        weakCallback.get().preExecute();
    }

    @Override
    protected ArrayList<MovieModel> doInBackground(String... strings) {
        Context context = weakContext.get();
        ArrayList<MovieModel> movieModels = new ArrayList<>();
        String type = strings[0];
        Cursor cursor;
        if (type.equals("movie")) {
            cursor = context.getContentResolver().query(CONTENT_URI_MOVIE, null, null, null, null);
        }else {
            cursor = context.getContentResolver().query(CONTENT_URI_TV, null, null, null, null);
        }
        if (cursor != null) {
            movieModels = MappingHelper.mapCursorToArrayList(cursor);
            cursor.close();
        }
        return movieModels;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieModel> movieModels) {
        super.onPostExecute(movieModels);
        weakCallback.get().postExecute(movieModels);
    }
}
