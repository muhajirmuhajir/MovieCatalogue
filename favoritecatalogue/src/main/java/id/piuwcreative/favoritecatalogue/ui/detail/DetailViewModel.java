package id.piuwcreative.favoritecatalogue.ui.detail;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

import id.piuwcreative.favoritecatalogue.R;
import id.piuwcreative.favoritecatalogue.model.MovieModel;
import id.piuwcreative.favoritecatalogue.model.TvModel;

import static id.piuwcreative.favoritecatalogue.utils.Const.BASE_IMAGE_URL;


public class DetailViewModel extends ViewModel {
    private static Application application;
    private MovieModel movie;
    private TvModel tvShow;
    private OnInsertDelete callback;


    private boolean allReady;

    public void init(Application application, OnInsertDelete callback) {
        this.application = application;
        this.callback = callback;

    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }


    public void setAllReady(boolean isReady) {
        this.allReady = isReady;
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

    void setTvShow(TvModel tvShow) {
        this.tvShow = tvShow;
    }

    public void addFavorite(View view) {
        if (movie != null) {
            //application.getContentResolver().insert();
            callback.insertSuccess();
        }
        if (tvShow != null) {
            callback.insertSuccess();
        }
    }

    public void deleteFavorite(View view) {
        if (movie != null) {
            callback.deleteSuccess();
        }
        if (tvShow != null) {
            callback.deleteSuccess();
        }

    }

    public void backToHome(View view) {
        callback.onFinish();
    }

    @BindingAdapter({"app:setRating"})
    public static void loadRatingBar(RatingBar view, double rating) {
        view.setRating((float) rating / 2f);
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        String imgUrl = BASE_IMAGE_URL + imageUrl;
        Picasso.get().load(imgUrl).into(view);
    }

    @BindingAdapter({"app:setStateBackground"})
    public static void setBackground(LinearLayout layout, boolean state) {
        if (state) {
            layout.setBackground(application.getDrawable(R.drawable.btn_delete));
        } else {
            layout.setBackground(application.getDrawable(R.drawable.btn_favorite));
        }
    }

    @BindingAdapter({"app:setStateImage"})
    public static void setImageState(ImageView view, boolean state) {
        if (state) {
            view.setImageDrawable(application.getDrawable(R.drawable.ic_delete));
        } else {
            view.setImageDrawable(application.getDrawable(R.drawable.ic_favorite));
        }
    }

    @BindingAdapter({"app:setStateText"})
    public static void setTextState(TextView view, boolean state) {
        if (state) {
            view.setText(R.string.delete);
        } else {
            view.setText(R.string.favorite);
        }
    }
}
