package id.piuwcreative.favoritecatalogue.ui.detail;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import id.piuwcreative.favoritecatalogue.R;
import id.piuwcreative.favoritecatalogue.databinding.ActivityDetailBinding;
import id.piuwcreative.favoritecatalogue.model.MovieModel;
import id.piuwcreative.favoritecatalogue.model.TvModel;

public class DetailActivity extends AppCompatActivity implements OnInsertDelete {
    public static final String EXTRA_DATA = "data_from_adapter";
    public static final String EXTRA_TYPE = "type_from_adapter";
    ActivityDetailBinding dataBinding;
    DetailViewModel viewModel;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        if (getIntent() != null) {
            viewModel.init(getApplication(), this);
            type = getIntent().getStringExtra(EXTRA_TYPE);
            if (type.equals("movie")) {
                MovieModel model = getIntent().getParcelableExtra(EXTRA_DATA);
                if (model != null) {
                    viewModel.setMovie(model);

                }
            } else if (type.equals("tv")) {
                TvModel model = getIntent().getParcelableExtra(EXTRA_DATA);
                if (model != null) {
                    viewModel.setTvShow(model);
                }
            }
            dataBinding.setViewModel(viewModel);
        }


    }


    @Override
    public void insertSuccess() {
        viewModel.setAllReady(true);
        dataBinding.setViewModel(viewModel);
        showToast("Data berhasil ditambahkan");
    }


    @Override
    public void deleteSuccess() {
        viewModel.setAllReady(false);
        dataBinding.setViewModel(viewModel);
        showToast("Data berhasil dihapus");
    }

    @Override
    public void onFinish() {
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
