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
    private OnSearchResultListener callback;

    public void init(OnSearchResultListener callback) {
        this.callback = callback;
        repository = new MovieRepository(listener);
    }

    public void search(View view) {
        if (keyword == null) {
            callback.onEmptyField();
        } else {
            callback.onLoadStarted();
            repository.setSearchMovie(keyword);
            repository.setSearchTv(keyword);
        }
    }

    private OnSearchResultListener listener = new OnSearchResultListener() {
        @Override
        public void onMovieResult(ArrayList<MovieModel> models) {
            callback.onMovieResult(models);
        }

        @Override
        public void onTvResult(ArrayList<TvModel> models) {
            callback.onTvResult(models);
        }

        @Override
        public void onEmptyResult() {
            callback.onEmptyResult();
        }

        @Override
        public void onEmptyField() {

        }

        @Override
        public void onLoadStarted() {

        }
    };

}
