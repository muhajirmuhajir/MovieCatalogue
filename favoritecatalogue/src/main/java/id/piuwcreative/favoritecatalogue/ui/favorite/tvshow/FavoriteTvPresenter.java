package id.piuwcreative.favoritecatalogue.ui.favorite.tvshow;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;

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
        Cursor cursor = application.getContentResolver().query(CONTENT_URI, null, null, null, null, null);

        if (cursor != null) {
            callback.showAllTv(MappingHelper.cursorToTv(cursor));
        }
    }
}
