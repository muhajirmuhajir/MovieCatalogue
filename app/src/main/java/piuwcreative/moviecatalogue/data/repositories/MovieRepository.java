package piuwcreative.moviecatalogue.data.repositories;

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
    private MutableLiveData<ArrayList<MovieModel>> movies;
    private MutableLiveData<ArrayList<MovieModel>> searchMovies;
    private MutableLiveData<ArrayList<TvModel>> tvShows;
    private MutableLiveData<ArrayList<TvModel>> searchTv;

    public static MovieRepository getInstance(){
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public MovieRepository() {
        movies = new MutableLiveData<>();
        searchMovies = new MutableLiveData<>();
        tvShows = new MutableLiveData<>();
        searchTv = new MutableLiveData<>();
    }

    public LiveData<ArrayList<MovieModel>> getSearchMovie(String query) {
        ApiMovieService.Api.getService().getMovieSearch(query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchMovies.postValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return searchMovies;
    }

    public LiveData<ArrayList<TvModel>> getSearchTv(String query) {
        ApiMovieService.Api.getService().getTvSearch(query).enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchTv.postValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {

            }
        });

        return searchTv;
    }

    public LiveData<ArrayList<MovieModel>> getAllMovie() {
        ApiMovieService.Api.getService().getAllMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
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
