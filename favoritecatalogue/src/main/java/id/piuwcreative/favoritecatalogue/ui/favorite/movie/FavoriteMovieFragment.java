package id.piuwcreative.favoritecatalogue.ui.favorite.movie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.piuwcreative.favoritecatalogue.R;
import id.piuwcreative.favoritecatalogue.adapter.MovieAdapter;
import id.piuwcreative.favoritecatalogue.model.MovieModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements MovieFavoriteView.View {
    private MovieAdapter adapter;
    private FavoriteMoviePresenter presenter;
    private RecyclerView recyclerView;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favorite_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavoriteMoviePresenter(this);
        adapter = new MovieAdapter(getContext());
        recyclerView = view.findViewById(R.id.rv_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

       presenter.getDataListMovie(getActivity().getApplication());

    }


    @Override
    public void onResume() {
        super.onResume();
       presenter.getDataListMovie(getActivity().getApplication());
    }

    @Override
    public void showAllMovie(List<MovieModel> models) {
        adapter.setMovieModels(models);
    }
}
