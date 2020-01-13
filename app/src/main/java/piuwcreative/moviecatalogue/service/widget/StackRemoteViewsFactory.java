package piuwcreative.moviecatalogue.service.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.data.database.AppDatabase;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

import static piuwcreative.moviecatalogue.utils.Const.BASE_IMAGE_URL;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private static ArrayList<Bitmap> bitmaps = new ArrayList<>();

    public StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        bitmaps.clear();
        AppDatabase database = AppDatabase.getInstance(context);
        List<MovieModel> models = database.movieDao().getAllMovie();
        if (models != null) {
            for (MovieModel model : models) {
                try {
                    URL url = new URL(BASE_IMAGE_URL + model.getPoster());
                    bitmaps.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        List<TvModel> tvModels = database.movieDao().getAllTv();
        if (tvModels != null) {
            for (TvModel model : tvModels) {
                try {
                    URL url = new URL(BASE_IMAGE_URL + model.getPoster());
                    bitmaps.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setImageViewBitmap(R.id.img_poster, bitmaps.get(i));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
