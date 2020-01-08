package piuwcreative.moviecatalogue.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Locale;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.viewModel.MainViewModel;
import piuwcreative.moviecatalogue.adapter.MovieAdapter;
import piuwcreative.moviecatalogue.model.MovieModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MovieAdapter adapter;
    private String TYPE = "tv";
    private static final String EXTRA_DATA = "data_state";

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        showLoading(true);
        recyclerView = view.findViewById(R.id.rv_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        if (Locale.getDefault().getDisplayLanguage().equalsIgnoreCase("indonesia")) {
            mainViewModel.setGenres("tv", "id-ID");
        } else {
            mainViewModel.setGenres("tv", "en-EN");
        }


        mainViewModel.getData().observe(this, new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> movieModels) {
                Log.i("jalanji", "jalanji");
                adapter.setData(movieModels);
                adapter.setType(TYPE);
                showLoading(false);
            }
        });
    }

    private void showLoading(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
