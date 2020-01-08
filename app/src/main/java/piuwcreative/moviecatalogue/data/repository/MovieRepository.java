package piuwcreative.moviecatalogue.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.data.network.ApiMovieService;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.MovieResponse;
import piuwcreative.moviecatalogue.model.TvModel;
import piuwcreative.moviecatalogue.model.TvResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository instance;

    public static MovieRepository getInstance(){
        if (instance == null) {
            instance = new MovieRepository();
        }

        return instance;
    }

    public MovieRepository() {
    }

    public LiveData<ArrayList<MovieModel>> getAllMovie() {
        final MutableLiveData<ArrayList<MovieModel>> movies = new MutableLiveData<>();

        ApiMovieService.Api.getService().getAllMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movies.postValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return movies;

    }

    public LiveData<ArrayList<TvModel>> getAllTvShow() {
        final MutableLiveData<ArrayList<TvModel>> tvShows = new MutableLiveData<>();

        ApiMovieService.Api.getService().getAllTvShow().enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    tvShows.postValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {

            }
        });

        return tvShows;
    }

}
