package piuwcreative.moviecatalogue.ui.favorite.movie;

import android.app.Application;

import piuwcreative.moviecatalogue.data.database.AppDatabase;

public class FavoriteMoviePresenter implements MovieFavoriteView.Presenter {
    private MovieFavoriteView.View view;
    private AppDatabase appDatabase;

    public FavoriteMoviePresenter(MovieFavoriteView.View view) {
        this.view = view;

    }

    @Override
    public void getDataListMovie(Application application) {
        appDatabase = AppDatabase.getInstance(application);
        if (appDatabase.movieDao().getAllMovie() != null) {
            view.showAllMovie(appDatabase.movieDao().getAllMovie());
        }
    }
}
