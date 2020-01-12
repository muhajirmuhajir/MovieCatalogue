package piuwcreative.moviecatalogue.ui.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.adapter.MovieAdapter;
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

    private MovieAdapter movieAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(SearchViewModel.class);
        viewModel.init(getContext(), this);
        dataBinding.setViewmodel(viewModel);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToggleSwitch toggleSwitch = dataBinding.toggleSwitches;

        toggleSwitch.getCheckedTogglePosition();

        RecyclerView recyclerView = dataBinding.rvContainer;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter(getContext());
        recyclerView.setAdapter(movieAdapter);

        viewModel.getAllMovie().observe(getViewLifecycleOwner(), new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> models) {
                movieAdapter.setMovieModels(models);
            }
        });
    }

    @Override
    public void onMovieResut(ArrayList<MovieViewModel> models) {

    }

    @Override
    public void onTvResult(ArrayList<TvModel> models) {

    }
}
