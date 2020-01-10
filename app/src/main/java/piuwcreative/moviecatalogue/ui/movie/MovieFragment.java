package piuwcreative.moviecatalogue.ui.movie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.adapter.MovieAdapter;
import piuwcreative.moviecatalogue.model.MovieModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements OnMovieLoad {
    private ShimmerFrameLayout shimmerFrameLayout;
    private MovieAdapter adapter;
    private MovieViewModel movieViewModel;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shimmerFrameLayout = view.findViewById(R.id.shimmer_layout);
        RecyclerView recyclerView = view.findViewById(R.id.rv_container);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(getContext());
        recyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            movieViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
            if (savedInstanceState == null) {
                movieViewModel.init(this);
            }
            movieViewModel.getAllMovie().observe(getActivity(), observer);
        }

    }

    private Observer<ArrayList<MovieModel>> observer = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(ArrayList<MovieModel> models) {
            adapter.setMovieModels(models);
            showLoading(false);
        }
    };

    @Override
    public void onStarted() {
        showLoading(true);
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

}
