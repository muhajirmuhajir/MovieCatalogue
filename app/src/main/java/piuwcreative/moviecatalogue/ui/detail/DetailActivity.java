package piuwcreative.moviecatalogue.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import piuwcreative.moviecatalogue.R;

public class DetailActivity extends AppCompatActivity {
    public static String EXTRA_DATA = "data";
    public static String EXTRA_TYPE = "type";
    private static final String STATE = "state";

    private TextView title, year, director, description, genres;
    private ImageView poster;

    ProgressBar progressBar;


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


        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE, getIntent().getParcelableExtra(EXTRA_DATA));
        outState.putString(EXTRA_TYPE, getIntent().getStringExtra(EXTRA_TYPE));
    }


}
