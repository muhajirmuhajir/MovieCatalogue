package piuwcreative.moviecatalogue.ui.fragments;


import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.adapter.MovieAdapter;
import piuwcreative.moviecatalogue.interfaces.LoadMovieCallback;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.service.LoadMovieAsync;

import static piuwcreative.moviecatalogue.database.DatabaseContract.TvColumns.CONTENT_URI_TV;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {
    private MovieAdapter adapter;
    private static final String EXTRA_DATA = "data";


    private ImageView imageViewStateEmpty;
    private TextView textViewStateEmpty;

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

        imageViewStateEmpty = view.findViewById(R.id.img_state_empty);
        textViewStateEmpty = view.findViewById(R.id.tv_img_state);


        RecyclerView recyclerView = view.findViewById(R.id.rv_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(getContext());
        adapter.setType("tv");
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        HandlerThread handlerThread = new HandlerThread("TvObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver dataObserver = new DataObserver(handler, getContext(), loadMovieCallback);
        getActivity().getContentResolver().registerContentObserver(CONTENT_URI_TV, true, dataObserver);

        if (savedInstanceState == null) {
            new LoadMovieAsync(getContext(), loadMovieCallback).execute("tv");
        } else {
            ArrayList<MovieModel> movieModels = savedInstanceState.getParcelableArrayList(EXTRA_DATA);
            if (movieModels != null) {
                adapter.setData(movieModels);
                showStateEmpty(false);
                if (movieModels.size() == 0) {
                    showStateEmpty(true);
                }
            } else {
                adapter.setData(new ArrayList<MovieModel>());
                showStateEmpty(false);
            }


        }
    }

    private LoadMovieCallback loadMovieCallback = new LoadMovieCallback() {
        @Override
        public void preExecute() {
            //TODO : Tambahkan progress dialog
        }

        @Override
        public void postExecute(ArrayList<MovieModel> models) {
            if (models.size() > 0) {
                adapter.setData(models);
                showStateEmpty(false);
            } else {
                adapter.setData(new ArrayList<MovieModel>());
                showStateEmpty(true);
            }
        }
    };

    public void showStateEmpty(boolean state) {
        if (state) {
            imageViewStateEmpty.setVisibility(View.VISIBLE);
            textViewStateEmpty.setVisibility(View.VISIBLE);
        } else {
            imageViewStateEmpty.setVisibility(View.GONE);
            textViewStateEmpty.setVisibility(View.GONE);
        }
    }

    public static class DataObserver extends ContentObserver {
        private Context context;
        private LoadMovieCallback loadMovieCallback;

        public DataObserver(Handler handler, Context context, LoadMovieCallback callback) {
            super(handler);
            this.context = context;
            this.loadMovieCallback = callback;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadMovieAsync(context, loadMovieCallback).execute("tv");
        }
    }
}
