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

import piuwcreative.moviecatalogue.interfaces.CustomOnItemClickListener;
import piuwcreative.moviecatalogue.ui.activities.DetailActivity;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.R;

import static piuwcreative.moviecatalogue.ui.activities.DetailActivity.EXTRA_DATA;
import static piuwcreative.moviecatalogue.ui.activities.DetailActivity.EXTRA_TYPE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieModel> movieModels = new ArrayList<>();
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieModel> items) {
        movieModels.clear();
        movieModels.addAll(items);
        notifyDataSetChanged();
    }

    public ArrayList<MovieModel> getMovieModels() {
        return movieModels;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(movieModels.get(position).getTitle());
        holder.description.setText(movieModels.get(position).getDescription());
        String image = movieModels.get(position).getPoster();

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, onItemClickCallback));

        Picasso.get().load(image).into(holder.photo);
    }

    private CustomOnItemClickListener.OnItemClickCallback onItemClickCallback = new CustomOnItemClickListener.OnItemClickCallback() {
        @Override
        public void onItemClicked(View view, int position) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(EXTRA_DATA, movieModels.get(position));
            intent.putExtra(EXTRA_TYPE, type);
            context.startActivity(intent);
        }
    };

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            description = itemView.findViewById(R.id.tv_description);
            photo = itemView.findViewById(R.id.img_poster);

        }
    }
}
