package id.piuwcreative.favoritecatalogue.ui.favorite.movie;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;

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
        Cursor cursor = application.getContentResolver().query(CONTENT_URI, null, null, null, null, null);

        if (cursor != null) {
            view.showAllMovie(MappingHelper.cursorToMovies(cursor));
        }
    }
}
