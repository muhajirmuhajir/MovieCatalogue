package piuwcreative.moviecatalogue.ui.search;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.model.TvModel;
import piuwcreative.moviecatalogue.ui.movie.MovieViewModel;

public interface OnSearchResultListener {
    void onMovieResut(ArrayList<MovieViewModel> models);
    void onTvResut(ArrayList<TvModel> models);

}
