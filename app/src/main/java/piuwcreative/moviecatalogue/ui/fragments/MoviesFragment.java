package piuwcreative.moviecatalogue.ui.fragments;


import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Locale;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.viewModel.MainViewModel;
import piuwcreative.moviecatalogue.adapter.MovieAdapter;
import piuwcreative.moviecatalogue.model.MovieModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private String TYPE = "movie";
    private MovieAdapter adapter;
    private MainViewModel mainViewModel;

    private static final String EXTRA_DATA = "data_state";

    public MoviesFragment() {
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

        progressBar = view.findViewById(R.id.progressBar);
        showLoading(true);
        recyclerView = view.findViewById(R.id.rv_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.i("jalanji", "jalanji first");
        adapter = new MovieAdapter(getContext());
        adapter.setType(TYPE);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        if (savedInstanceState == null) {
            loadFromApi();
        } else {
            ArrayList<MovieModel> models = savedInstanceState.getParcelableArrayList(EXTRA_DATA);
            adapter.setData(models);
            if (adapter.getItemCount() == 0) {
                loadFromApi();
            }
            showLoading(false);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_DATA, adapter.getMovieModels());
    }

    private void loadFromApi() {
        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        if (Locale.getDefault().getDisplayLanguage().equalsIgnoreCase("indonesia")) {
            mainViewModel.setGenres(TYPE, "id-ID");
        } else {
            mainViewModel.setGenres(TYPE, "en-EN");
        }


        mainViewModel.getData().observe(this, listObserver);

    }

    private Observer<ArrayList<MovieModel>> listObserver = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(ArrayList<MovieModel> models) {
            adapter.setData(models);
            showLoading(false);
        }
    };

    private void showLoading(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
