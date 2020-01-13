package id.piuwcreative.favoritecatalogue.ui.favorite.movie;

import android.app.Application;

import java.util.List;

import id.piuwcreative.favoritecatalogue.model.MovieModel;


public interface MovieFavoriteView {
    interface View {
        void showAllMovie(List<MovieModel> models);
    }

    interface Presenter {
        void getDataListMovie(Application application);
    }
}
