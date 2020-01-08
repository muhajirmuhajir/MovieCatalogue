package piuwcreative.moviecatalogue.ui.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.helper.MappingHelper;
import piuwcreative.moviecatalogue.model.MovieModel;

import static piuwcreative.moviecatalogue.database.DatabaseContract.MovieColumns.CONTENT_URI_MOVIE;
import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.CONTENT_URI_TV;

public class DetailActivity extends AppCompatActivity {
    public static String EXTRA_DATA = "data";
    public static String EXTRA_TYPE = "type";
    private static final String STATE = "state";

    private TextView title, year, director, description, genres;
    private ImageView poster;

    ProgressBar progressBar;
    MovieModel model;

    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        title = findViewById(R.id.title_movie);
        year = findViewById(R.id.tv_year);
        director = findViewById(R.id.tv_director);
        description = findViewById(R.id.tv_description);
        genres = findViewById(R.id.tv_genres);
        poster = findViewById(R.id.img_poster);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        if (savedInstanceState == null) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    model = getIntent().getParcelableExtra(EXTRA_DATA);
                    type = getIntent().getStringExtra(EXTRA_TYPE);
                    bind(model);
                }
            }, 500);
        } else {
            model = savedInstanceState.getParcelable(STATE);
            type = savedInstanceState.getString(EXTRA_TYPE);
            bind(model);

        }

        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        MenuItem fav = menu.findItem(R.id.add_favorite);
        if (exist(type)) {
            fav.setIcon(R.drawable.ic_favorite);
        } else {
            fav.setIcon(R.drawable.ic_favorite_outline);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_favorite:
                if (!exist(type)) {
                    ContentValues values = MappingHelper.mapObjectToContentValues(model);
                    getContentResolver().insert(type.equals("movie")?CONTENT_URI_MOVIE: CONTENT_URI_TV, values);
                    Toast.makeText(this, "Sukses Menambahkan " + model.getTitle() + " ke favorite " + type, Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.ic_favorite);
                } else {
                    Uri uriWithId = type.equals("movie")? Uri.parse(CONTENT_URI_MOVIE + "/"+ model.getId())
                            : Uri.parse(CONTENT_URI_TV +"/"+ model.getId());
                    getContentResolver().delete(uriWithId, null, null);
                    Toast.makeText(this, "Menghapus " + model.getTitle() + " dari favorite " + type, Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.ic_favorite_outline);
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bind(MovieModel model) {
        title.setText(model.getTitle());
        year.setText(model.getYear());
        director.setText(model.getRating());
        description.setText(model.getDescription());
        genres.setText(model.getGenres());
        Picasso.get().load(model.getPoster()).into(poster);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE, getIntent().getParcelableExtra(EXTRA_DATA));
        outState.putString(EXTRA_TYPE, getIntent().getStringExtra(EXTRA_TYPE));
    }

    private boolean exist(String type) {
        MovieModel model = getIntent().getParcelableExtra(EXTRA_DATA);
        boolean exists = false;
        Uri uriWithId;
        if (type.equals("movie")) {
            uriWithId = Uri.parse(CONTENT_URI_MOVIE + "/" + model.getId());
        } else {
            uriWithId = Uri.parse(CONTENT_URI_TV + "/" + model.getId());
        }

        if (uriWithId != null) {
            Cursor cursor = getContentResolver().query(uriWithId, null, null, null, null);

            if (cursor != null) {
                exists = cursor.getCount() > 0;
                cursor.close();
            }
        }


        return exists;
    }

}
