package piuwcreative.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.data.repository.MovieRepository;
import piuwcreative.moviecatalogue.model.MovieModel;


public class MovieViewModel extends ViewModel {

    private LiveData<ArrayList<MovieModel>> allMovie;
    private MovieRepository movieRepository;

    public void init(OnMovieLoad callback) {
        callback.onStarted();
        movieRepository = MovieRepository.getInstance();
    }

    private void setAllMovie() {
        this.allMovie = movieRepository.getAllMovie();
    }

    public LiveData<ArrayList<MovieModel>> getAllMovie() {
        if (allMovie == null) {
            setAllMovie();
        }
        return allMovie;
    }
}
