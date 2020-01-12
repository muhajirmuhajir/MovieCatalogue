package piuwcreative.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.ui.detail.DetailActivity;

import static piuwcreative.moviecatalogue.ui.detail.DetailActivity.EXTRA_DATA;
import static piuwcreative.moviecatalogue.ui.detail.DetailActivity.EXTRA_TYPE;
import static piuwcreative.moviecatalogue.utils.Const.BASE_IMAGE_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private static final String TYPE = "movie";
    private List<MovieModel> movieModels = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movieModels.get(position));

        final int positionAt = holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(EXTRA_DATA, movieModels.get(positionAt));
                intent.putExtra(EXTRA_TYPE, TYPE);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView poster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            poster = itemView.findViewById(R.id.img_poster);
            description = itemView.findViewById(R.id.tv_description);
        }

        public void bind(MovieModel model) {
            title.setText(model.getTitle());
            description.setText(model.getOverview());
            Picasso.get().load(BASE_IMAGE_URL+model.getPoster()).into(poster);
        }
    }
}
