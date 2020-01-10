package piuwcreative.moviecatalogue.ui.detail;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

import piuwcreative.moviecatalogue.data.repository.FavoriteMovieRepository;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

import static piuwcreative.moviecatalogue.utils.Const.BASE_IMAGE_URL;

public class DetailViewModel extends ViewModel {
    private MovieModel movie;
    private TvModel tvShow;
    private FavoriteMovieRepository favoriteMovieRepository;
    private OnInsertDelete callback;


    public boolean allReady;

    public void init(Application application, OnInsertDelete callback) {
        this.callback = callback;
        favoriteMovieRepository = FavoriteMovieRepository.getInstance(application);
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }


    public void setAllReady(String type, int id) {
        if (type.equals("movie")) {
            MovieModel model = favoriteMovieRepository.findMovie(id);
            if (model != null) {
                Log.i("jalanji", model.getTitle());
            }
            allReady = model != null;
        } else if (type.equals("tv")) {
            TvModel model = favoriteMovieRepository.findTv(id);
            allReady = model != null;
        } else {
            allReady = false;
        }
    }

    public void setAllReady(boolean state) {
        this.allReady = state;
    }

    public boolean isAllReady() {
        return allReady;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public TvModel getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvModel tvShow) {
        this.tvShow = tvShow;
    }

    public void addFavorite(View view) {
        Log.i("jalanji", "method add");
        if (movie != null) {
            favoriteMovieRepository.insertMovie(movie);
            callback.insertSuccess();
        }
        if (tvShow != null) {
            favoriteMovieRepository.insertTv(tvShow);
            callback.insertSuccess();
        }
    }

    public void deleteFavorite(View view) {
        Log.i("jalanji", "method delete");
        if (movie != null) {
            favoriteMovieRepository.deleteMovie(movie);
            callback.deleteSuccess();
        }
        if (tvShow != null) {
            favoriteMovieRepository.deleteTv(tvShow);
            callback.deleteSuccess();
        }

    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        String imgUrl = BASE_IMAGE_URL + imageUrl;
        Picasso.get().load(imgUrl).into(view);
    }
}
