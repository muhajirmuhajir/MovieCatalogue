package piuwcreative.moviecatalogue.ui.search;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.adapter.MovieAdapter;
import piuwcreative.moviecatalogue.adapter.TvAdapter;
import piuwcreative.moviecatalogue.databinding.FragmentSearchBinding;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.model.TvModel;
import piuwcreative.moviecatalogue.ui.movie.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements OnSearchResultListener {
    private FragmentSearchBinding dataBinding;
    private SearchViewModel viewModel;
    private RecyclerView movieContainer;
    private RecyclerView tvContainer;
    private ShimmerFrameLayout shimmerFrameLayout;
    private MovieAdapter movieAdapter;
    private TvAdapter tvAdapter;

    private static final String EXTRA_MOVIE = "extra_mov";
    private static final String EXTRA_TV = "extra_tv";

    private static ArrayList<MovieModel> movieModels;
    private static ArrayList<TvModel> tvModels;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(SearchViewModel.class);
        viewModel.init(this);
        dataBinding.setViewmodel(viewModel);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToggleSwitch toggleSwitch = dataBinding.toggleSwitches;



        movieContainer = dataBinding.rvContainer;
        tvContainer = dataBinding.rvContainer1;
        shimmerFrameLayout = dataBinding.shimmerLayout;

        movieContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        tvContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        movieAdapter = new MovieAdapter(getContext());
        tvAdapter = new TvAdapter(getContext());

        movieContainer.setAdapter(movieAdapter);
        tvContainer.setAdapter(tvAdapter);

        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                changeTab(position);
            }
        });

        if (savedInstanceState != null) {
            tvAdapter.setModels(savedInstanceState.<TvModel>getParcelableArrayList(EXTRA_TV));
            movieAdapter.setMovieModels(savedInstanceState.<MovieModel>getParcelableArrayList(EXTRA_MOVIE));
        }

    }


    @Override
    public void onMovieResult(ArrayList<MovieModel> models) {
        movieAdapter.setMovieModels(models);
        if (models.size() == 0) {
            Toast.makeText(getContext(), "Movie not found", Toast.LENGTH_SHORT).show();
        } else {
            movieModels = new ArrayList<>(models);
        }
        showLoading(false);
    }

    @Override
    public void onTvResult(ArrayList<TvModel> models) {
        tvAdapter.setModels(models);
        if (models.size() == 0) {
            Toast.makeText(getContext(), "Tv not found", Toast.LENGTH_SHORT).show();
        } else {
            tvModels = new ArrayList<>(models);
        }
        showLoading(false);
    }

    @Override
    public void onEmptyResult() {

    }

    @Override
    public void onEmptyField() {
        Toast.makeText(getContext(), "Field is empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadStarted() {
        showLoading(true);
    }

    public void changeTab(int index) {
        if (index == 0) {
            movieContainer.setVisibility(View.VISIBLE);
            tvContainer.setVisibility(View.GONE);
        } else {
            movieContainer.setVisibility(View.GONE);
            tvContainer.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading(boolean state) {
        if (state) {
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            shimmerFrameLayout.startShimmer();
        } else {
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_MOVIE,movieModels);
        outState.putParcelableArrayList(EXTRA_TV,tvModels);
    }
}
