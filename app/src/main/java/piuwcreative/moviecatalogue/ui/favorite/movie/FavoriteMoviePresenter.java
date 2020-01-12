package piuwcreative.moviecatalogue.ui.favorite.movie;

import android.app.Application;

import piuwcreative.moviecatalogue.data.database.AppDatabase;

public class FavoriteMoviePresenter implements MovieFavoriteView.Presenter {
    private MovieFavoriteView.View view;

    public FavoriteMoviePresenter(MovieFavoriteView.View view) {
        this.view = view;
    }

    @Override
    public void getDataListMovie(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        if (database.movieDao().getAllMovie() != null) {
            view.showAllMovie(database.movieDao().getAllMovie());
        }
    }
}
