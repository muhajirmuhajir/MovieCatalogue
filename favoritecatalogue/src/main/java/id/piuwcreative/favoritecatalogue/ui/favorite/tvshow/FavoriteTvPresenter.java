package id.piuwcreative.favoritecatalogue.ui.favorite.tvshow;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;

import id.piuwcreative.favoritecatalogue.utils.MappingHelper;

import static id.piuwcreative.favoritecatalogue.utils.Const.AUTHORITY;
import static id.piuwcreative.favoritecatalogue.utils.Const.TABLE_TV;


public class FavoriteTvPresenter implements FavoriteTvView.Presenter {
    private FavoriteTvView.View callback;

    private static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + TABLE_TV);

    public FavoriteTvPresenter(FavoriteTvView.View callback) {
        this.callback = callback;
    }

    @Override
    public void getDataListTv(Application application) {
        @SuppressLint("Recycle") ContentProviderClient client = application.getContentResolver().acquireContentProviderClient(CONTENT_URI);
        Cursor cursor = null;
        try {
            cursor = client.query(CONTENT_URI, null, null, null, null, null);
            if (cursor != null) {
                callback.showAllTv(MappingHelper.cursorToTv(cursor));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
