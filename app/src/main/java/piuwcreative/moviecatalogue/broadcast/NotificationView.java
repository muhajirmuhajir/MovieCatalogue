package piuwcreative.moviecatalogue.broadcast;

import android.content.Context;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.model.MovieModel;

public interface NotificationView {
    interface View{
        void setMovie(Context context, int id, ArrayList<MovieModel> models);
    }
    interface Presenter{
        void requestMovies(String date);
    }
}
