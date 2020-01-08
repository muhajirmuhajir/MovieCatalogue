package piuwcreative.moviecatalogue.interfaces;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.model.MovieModel;

public interface LoadMovieCallback {
    void preExecute();
    void postExecute(ArrayList<MovieModel> models);
}
