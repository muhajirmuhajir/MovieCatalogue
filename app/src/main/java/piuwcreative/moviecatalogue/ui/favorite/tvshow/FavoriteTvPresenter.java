package piuwcreative.moviecatalogue.ui.favorite.tvshow;

import android.app.Application;

import piuwcreative.moviecatalogue.data.database.AppDatabase;

public class FavoriteTvPresenter implements FavoriteTvView.Presenter {
    private FavoriteTvView.View callback;

    public FavoriteTvPresenter(FavoriteTvView.View callback) {
        this.callback = callback;
    }

    @Override
    public void getDataListTv(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        if (database.movieDao().getAllTv() != null) {
            callback.showAllTv(database.movieDao().getAllTv());
        }
    }
}
