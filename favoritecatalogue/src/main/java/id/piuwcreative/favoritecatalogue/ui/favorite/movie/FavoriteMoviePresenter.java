package id.piuwcreative.favoritecatalogue.ui.favorite.movie;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;

import id.piuwcreative.favoritecatalogue.utils.MappingHelper;

import static id.piuwcreative.favoritecatalogue.utils.Const.AUTHORITY;
import static id.piuwcreative.favoritecatalogue.utils.Const.TABLE_MOVIE;


public class FavoriteMoviePresenter implements MovieFavoriteView.Presenter {
    private MovieFavoriteView.View view;

    private static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY + "/" +TABLE_MOVIE);


    public FavoriteMoviePresenter(MovieFavoriteView.View view) {
        this.view = view;
    }

    @Override
    public void getDataListMovie(Application application) {
        @SuppressLint("Recycle") ContentProviderClient client = application.getContentResolver().acquireContentProviderClient(CONTENT_URI);
        Cursor cursor = null;
        try {
            cursor = client.query(CONTENT_URI, null, null, null, null, null);
            if (cursor != null) {
                view.showAllMovie(MappingHelper.cursorToMovies(cursor));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
