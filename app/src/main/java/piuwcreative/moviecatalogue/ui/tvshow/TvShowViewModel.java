package piuwcreative.moviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.data.repositories.MovieRepository;
import piuwcreative.moviecatalogue.model.TvModel;

public class TvShowViewModel extends ViewModel {
    private MovieRepository repository;
    private LiveData<ArrayList<TvModel>> allTv;

    public void init(OnTvLoad callback) {
        callback.onStarted();
        repository = MovieRepository.getInstance();
    }

    public LiveData<ArrayList<TvModel>> getAllTv() {
        if (allTv == null) {
            setAllTv();
        }
        return allTv;
    }

    private void setAllTv() {
        allTv = repository.getAllTvShow();
    }
}
