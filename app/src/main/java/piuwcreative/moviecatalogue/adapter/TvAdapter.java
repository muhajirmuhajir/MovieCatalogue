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

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.model.TvModel;
import piuwcreative.moviecatalogue.ui.detail.DetailActivity;

import static piuwcreative.moviecatalogue.ui.detail.DetailActivity.EXTRA_DATA;
import static piuwcreative.moviecatalogue.ui.detail.DetailActivity.EXTRA_TYPE;
import static piuwcreative.moviecatalogue.utils.Const.BASE_IMAGE_URL;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private static final String TYPE = "tv";
    private ArrayList<TvModel> models = new ArrayList<>();
    private Context context;

    public TvAdapter(Context context) {
        this.context = context;
    }

    public void setModels(ArrayList<TvModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(models.get(position));

        final int positionAt = holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(EXTRA_DATA, models.get(positionAt));
                intent.putExtra(EXTRA_TYPE, TYPE);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
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

        public void bind(TvModel model) {
            title.setText(model.getTitle());
            description.setText(model.getOverview());
            Picasso.get().load(BASE_IMAGE_URL + model.getPoster()).into(poster);
        }
    }
}
