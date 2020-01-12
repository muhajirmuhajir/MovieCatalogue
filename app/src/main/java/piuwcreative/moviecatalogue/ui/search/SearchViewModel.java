package piuwcreative.moviecatalogue.ui.search;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.data.repositories.MovieRepository;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

public class SearchViewModel extends ViewModel {
    public String keyword;
    private MovieRepository repository;
    private LiveData<ArrayList<MovieModel>> allMovie = new MutableLiveData<>();
    private LiveData<ArrayList<TvModel>> allTvShow = new MutableLiveData<>();
    private OnSearchResultListener callback;

    public void init(Context context, OnSearchResultListener callback) {
        this.callback = callback;
        repository = MovieRepository.getInstance();
    }

    public void search(View view) {
        if (keyword.isEmpty()) {
            Log.i("jalanji", "isi nul");
        } else {
            allMovie = repository.getSearchMovie(keyword);
            allTvShow = repository.getSearchTv(keyword);
        }
    }

    public LiveData<ArrayList<MovieModel>> getAllMovie() {
        return allMovie;
    }

    public LiveData<ArrayList<TvModel>> getAllTvShow() {
        return allTvShow;
    }
}
