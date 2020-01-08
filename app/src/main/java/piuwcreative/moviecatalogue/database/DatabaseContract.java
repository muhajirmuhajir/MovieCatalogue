package piuwcreative.moviecatalogue.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "piuwcreative.moviecatalogue";
    private static final String SCHEME = "content";

    public static String ID = "id";
    public static final class MovieColumns implements BaseColumns {
        public static String TABLE_MOVIE = "movie";
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String YEAR = "year";
        public static String RATING = "rating";
        public static String GENRES = "genres";
        public static String POSTER = "poster";

        public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }

    public static final class TvColumns implements BaseColumns{
        public static String TABLE_TV = "tv";
        public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
    }




}
