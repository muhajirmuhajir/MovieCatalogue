package id.piuwcreative.favoritecatalogue.ui.favorite.tvshow;


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
import id.piuwcreative.favoritecatalogue.adapter.TvAdapter;
import id.piuwcreative.favoritecatalogue.model.TvModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment implements FavoriteTvView.View {
    private RecyclerView recyclerView;
    private FavoriteTvPresenter presenter;
    private TvAdapter adapter;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_container);
        presenter = new FavoriteTvPresenter(this);
        adapter = new TvAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
       presenter.getDataListTv(getActivity().getApplication());
    }


    @Override
    public void onResume() {
        super.onResume();
       presenter.getDataListTv(getActivity().getApplication());
    }

    @Override
    public void showAllTv(List<TvModel> models) {
        adapter.setModels(models);
    }
}
