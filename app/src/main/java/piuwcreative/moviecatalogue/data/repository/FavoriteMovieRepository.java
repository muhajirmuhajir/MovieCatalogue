package piuwcreative.moviecatalogue.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import piuwcreative.moviecatalogue.data.database.AppDatabase;
import piuwcreative.moviecatalogue.data.database.DatabaseDao;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

public class FavoriteMovieRepository {
    private static FavoriteMovieRepository instance;
    private DatabaseDao databaseDao;
    private LiveData<List<MovieModel>> allMovie;
    private LiveData<List<TvModel>> allTv;

    public FavoriteMovieRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        databaseDao = database.movieDao();
        allMovie = databaseDao.getAllMovie();
        allTv = databaseDao.getAllTv();
    }

    public static FavoriteMovieRepository getInstance(Application application) {
        if (instance == null) {
            instance = new FavoriteMovieRepository(application);
        }
        return instance;
    }


    public void insertMovie(MovieModel model) {
        new InsertMovieAsyncTask(databaseDao).execute(model);
    }


    public void deleteMovie(MovieModel model) {
        new DeleteMovieAsyncTask(databaseDao).execute(model);
    }

    public MovieModel findMovie(int id) {
        return databaseDao.getMovieById(id);
    }

    public void insertTv(TvModel model) {
        new InsertTvAsyncTask(databaseDao).execute(model);
    }


    public void deleteTv(TvModel model) {
        new DeleteTvAsyncTask(databaseDao).execute(model);
    }

    public TvModel findTv(int id) {
        return databaseDao.getTvShowById(id);
    }

    public LiveData<List<MovieModel>> getAllMovie() {
        return allMovie;
    }

    public LiveData<List<TvModel>> getAllTv() {
        return allTv;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<MovieModel, Void, Void> {
        private DatabaseDao databaseDao;

        public InsertMovieAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(MovieModel... movieModels) {
            databaseDao.insertMovie(movieModels[0]);
            return null;
        }
    }

    private static class InsertTvAsyncTask extends AsyncTask<TvModel, Void, Void> {
        private DatabaseDao databaseDao;

        public InsertTvAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(TvModel... tvModels) {
            databaseDao.insertTv(tvModels[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<MovieModel, Void, Void> {
        private DatabaseDao databaseDao;

        public DeleteMovieAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(MovieModel... movieModels) {
            databaseDao.deleteMovie(movieModels[0]);
            return null;
        }
    }

    private static class DeleteTvAsyncTask extends AsyncTask<TvModel, Void, Void> {
        private DatabaseDao databaseDao;

        public DeleteTvAsyncTask(DatabaseDao databaseDao) {
            this.databaseDao = databaseDao;
        }

        @Override
        protected Void doInBackground(TvModel... tvModels) {
            databaseDao.deleteTvShow(tvModels[0]);
            return null;
        }
    }
}
