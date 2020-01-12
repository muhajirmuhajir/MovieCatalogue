package piuwcreative.moviecatalogue.ui.search;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

public interface OnSearchResultListener {
    void onMovieResult(ArrayList<MovieModel> models);

    void onTvResult(ArrayList<TvModel> models);

    void onEmptyResult();

    void onEmptyField();

    void onLoadStarted();
}
