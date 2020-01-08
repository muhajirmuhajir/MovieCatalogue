package piuwcreative.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.model.MovieModel;

public interface OnMovieLoad {
    void onStarted();

    void onSuccess(LiveData<ArrayList<MovieModel>> movies);
}
