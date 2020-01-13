package id.piuwcreative.favoritecatalogue.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import id.piuwcreative.favoritecatalogue.R;
import id.piuwcreative.favoritecatalogue.ui.favorite.movie.FavoriteMovieFragment;
import id.piuwcreative.favoritecatalogue.ui.favorite.tvshow.FavoriteTvFragment;


public class FavoritePagerAdapter extends FragmentStatePagerAdapter {
    private Context context;

    public FavoritePagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvFragment();
        }
        return new FavoriteMovieFragment();
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.movie,
            R.string.tvShow
    };

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }
}

