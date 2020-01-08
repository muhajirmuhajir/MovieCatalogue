package piuwcreative.moviecatalogue.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

@Database(entities = {MovieModel.class, TvModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "dbMovie";

    public abstract DatabaseDao movieDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        synchronized (AppDatabase.class) {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
            return instance;
        }
    }
}
