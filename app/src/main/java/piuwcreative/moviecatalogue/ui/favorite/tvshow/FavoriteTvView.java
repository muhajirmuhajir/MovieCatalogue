package piuwcreative.moviecatalogue.ui.favorite.tvshow;

import android.app.Application;

import java.util.List;

import piuwcreative.moviecatalogue.model.TvModel;

public interface FavoriteTvView {
    interface View{
        void showAllTv(List<TvModel> models);
    }
    interface Presenter{
        void getDataListTv(Application application);
    }
}
