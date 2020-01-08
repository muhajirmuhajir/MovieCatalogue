package piuwcreative.moviecatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.ui.favorite.FavoriteFragment;
import piuwcreative.moviecatalogue.ui.movie.MovieFragment;
import piuwcreative.moviecatalogue.ui.settings.SettingsActivity;
import piuwcreative.moviecatalogue.ui.tvshow.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    private static final String FRAGMENT_TAG = "piuwcreative.moviecatalogue.FRAGMENT_TAG";
    BottomNavigationView bottomNavigationView;
    Fragment active;


    private BottomNavigationView.OnNavigationItemSelectedListener callback = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_movies:
                    if (menuItem.getItemId() != bottomNavigationView.getSelectedItemId()) {
                        setFragment(new MovieFragment());
                    }
                    return true;
                case R.id.navigation_tvshow:
                    if (menuItem.getItemId() != bottomNavigationView.getSelectedItemId()) {
                        setFragment(new TvShowFragment());
                    }
                    return true;
                case R.id.navigation_favorite:
                    if (menuItem.getItemId() != bottomNavigationView.getSelectedItemId()) {
                        setFragment(new FavoriteFragment());
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(callback);


        if (savedInstanceState == null) {
            active = new MovieFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, active,FRAGMENT_TAG).commit();
            bottomNavigationView.setSelectedItemId(R.id.navigation_movies);
        } else {
            setFragment(getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_TAG));
        }


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, FRAGMENT_TAG, active);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment fragment) {
        this.active = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
    }
}
