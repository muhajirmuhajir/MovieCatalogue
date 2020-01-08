package piuwcreative.moviecatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
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
    BottomNavigationView bottomNavigationView;
    Fragment fragmentMovies;
    Fragment fragmentTv;
    Fragment fragmentFavorite;


    Fragment active;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_movies:
                    getSupportFragmentManager().beginTransaction().hide(active).show(fragmentMovies).commit();
                    active = fragmentMovies;
                    return true;
                case R.id.navigation_tvshow:
                    getSupportFragmentManager().beginTransaction().hide(active).show(fragmentTv).commit();
                    active = fragmentTv;
                    return true;
                case R.id.navigation_favorite:
                    getSupportFragmentManager().beginTransaction().hide(active).show(fragmentFavorite).commit();
                    active = fragmentFavorite;
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


        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt("position");
            bottomNavigationView.setSelectedItemId(position);

            fragmentMovies = getSupportFragmentManager().findFragmentByTag("1");
            fragmentFavorite = getSupportFragmentManager().findFragmentByTag("3");
            fragmentTv = getSupportFragmentManager().findFragmentByTag("2");


        } else if (fragmentMovies == null) {
            fragmentMovies = new MovieFragment();
            fragmentTv = new TvShowFragment();
            fragmentFavorite = new FavoriteFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, fragmentFavorite, "3").hide(fragmentFavorite).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, fragmentTv, "2").hide(fragmentTv).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, fragmentMovies, "1").commit();


        }
        active = getCurrentActiveFragment(bottomNavigationView.getSelectedItemId());
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    private Fragment getCurrentActiveFragment(int selectedId) {

        switch (selectedId) {
            case R.id.navigation_movies:
                return fragmentMovies;
            case R.id.navigation_tvshow:
                return fragmentTv;
            case R.id.navigation_favorite:
                return fragmentFavorite;
        }
        return fragmentMovies;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", bottomNavigationView.getSelectedItemId());
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
}
