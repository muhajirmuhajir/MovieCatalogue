package piuwcreative.moviecatalogue.service.broadcast;

import android.content.Context;

import piuwcreative.moviecatalogue.data.network.ApiMovieService;
import piuwcreative.moviecatalogue.model.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationPresenter implements NotificationView.Presenter {

    private Context context;
    private int notifId;
    private NotificationView.View view;

    public NotificationPresenter(Context context, int notifId, NotificationView.View view) {
        this.context = context;
        this.notifId = notifId;
        this.view = view;
    }

    @Override
    public void requestMovies(String date) {
        ApiMovieService.Api.getService().getTodayMovieRelease(date, date).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    view.setMovie(context, notifId, response.body().getResult());

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


}
