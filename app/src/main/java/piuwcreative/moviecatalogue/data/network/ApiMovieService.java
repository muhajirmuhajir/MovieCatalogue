package piuwcreative.moviecatalogue.data.network;

import piuwcreative.moviecatalogue.model.MovieResponse;
import piuwcreative.moviecatalogue.model.TvResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static piuwcreative.moviecatalogue.BuildConfig.API_KEY;

public interface ApiMovieService {

    @GET("discover/movie?api_key=" + API_KEY + "&language=en-EN")
    Call<MovieResponse> getAllMovie();

    @GET("discover/tv?api_key=" + API_KEY + "&language=en-EN")
    Call<TvResponse> getAllTvShow();

    @GET("search/movie?api_key="+API_KEY +"&language=en-EN")
    Call<MovieResponse> getMovieSearch(@Query("query") String query);

    @GET("search/tv?api_key="+API_KEY +"&language=en-EN")
    Call<TvResponse> getTvSearch(@Query("query") String query);

    @GET("discover/movie?api_key="+API_KEY)
    Call<MovieResponse> getTodayMovieRelease(@Query("primary_release_date.gte") String releaseDateGte,@Query("primary_release_date.lte") String releaseDateLte);

    class Api {
        private static final String BASE_URL = "https://api.themoviedb.org/3/";

        public static ApiMovieService getService() {
            return getRetrofitClient().create(ApiMovieService.class);
        }

        private static Retrofit getRetrofitClient() {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }
}



