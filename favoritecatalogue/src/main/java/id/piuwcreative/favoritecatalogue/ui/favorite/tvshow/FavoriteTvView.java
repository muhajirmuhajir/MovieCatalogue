package id.piuwcreative.favoritecatalogue.ui.favorite.tvshow;

import android.app.Application;

import java.util.List;

import id.piuwcreative.favoritecatalogue.model.TvModel;


public interface FavoriteTvView {
    interface View{
        void showAllTv(List<TvModel> models);
    }
    interface Presenter{
        void getDataListTv(Application application);
    }
}
