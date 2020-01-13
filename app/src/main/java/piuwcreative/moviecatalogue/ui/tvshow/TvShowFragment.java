package piuwcreative.moviecatalogue.ui.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import piuwcreative.moviecatalogue.adapter.TvAdapter;
import piuwcreative.moviecatalogue.model.TvModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements OnTvLoad {
    private TvShowViewModel viewModel;
    private TvAdapter adapter;
    private ShimmerFrameLayout shimmerFrameLayout;

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

        shimmerFrameLayout = view.findViewById(R.id.shimmer_layout);
        RecyclerView recyclerView = view.findViewById(R.id.rv_container);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TvAdapter(getContext());
        recyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
            if (savedInstanceState == null) {
                viewModel.init(this);
            }
            viewModel.getAllTv().observe(getActivity(), observer);
        }

    }

    private Observer<ArrayList<TvModel>> observer = new Observer<ArrayList<TvModel>>() {
        @Override
        public void onChanged(ArrayList<TvModel> tvModels) {
            adapter.setModels(tvModels);
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
            shimmerFrameLayout.setVisibility(View.GONE);
            shimmerFrameLayout.stopShimmer();
        }
    }
}
