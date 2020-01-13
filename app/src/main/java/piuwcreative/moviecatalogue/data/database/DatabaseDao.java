package piuwcreative.moviecatalogue.data.database;


import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;

@Dao
public interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieModel model);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTv(TvModel tvModel);

    @Delete
    void deleteMovie(MovieModel model);

    @Delete
    void deleteTvShow(TvModel model);

    @Query("SELECT * FROM movie_table ORDER BY title ASC")
    List<MovieModel> getAllMovie();

    @Query("SELECT * FROM tv_table ORDER BY title ASC")
    List<TvModel> getAllTv();

    @Query("SELECT * FROM MOVIE_TABLE WHERE id = :id")
    MovieModel getMovieById(int id);

    @Query("SELECT * FROM TV_TABLE WHERE id = :id")
    TvModel getTvShowById(int id);

    @Query("SELECT * FROM MOVIE_TABLE WHERE title LIKE :title")
    LiveData<List<MovieModel>> getMovieByTitle(String title);

    @Query("SELECT * FROM TV_TABLE WHERE title LIKE :title")
    LiveData<List<TvModel>> getTvShowByTitle(String title);

    @Query("SELECT * FROM movie_table ORDER BY title ASC")
    Cursor getAllMovieProvider();

    @Query("SELECT * FROM tv_table ORDER BY title ASC")
    Cursor getAllTvProvider();
}
