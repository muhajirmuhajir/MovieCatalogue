package piuwcreative.moviecatalogue.ui.movie;

import androidx.lifecycle.ViewModel;

import piuwcreative.moviecatalogue.data.repository.MovieRepository;


public class MovieViewModel extends ViewModel {
    private OnMovieLoad callback;


    public void init(OnMovieLoad callback) {
        this.callback = callback;
        callback.onStarted();
    }

    public void getAllMovie() {
        MovieRepository movieRepository = MovieRepository.getInstance();
        callback.onSuccess(movieRepository.getAllMovie());
    }
}
