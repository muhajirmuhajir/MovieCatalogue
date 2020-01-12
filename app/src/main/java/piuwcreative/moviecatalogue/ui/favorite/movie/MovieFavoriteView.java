package piuwcreative.moviecatalogue.ui.favorite.movie;

import android.app.Application;

import java.util.List;

import piuwcreative.moviecatalogue.model.MovieModel;

public interface MovieFavoriteView {
    interface View {
        void showAllMovie(List<MovieModel> models);
    }

    interface Presenter {
        void getDataListMovie(Application application);
    }
}
