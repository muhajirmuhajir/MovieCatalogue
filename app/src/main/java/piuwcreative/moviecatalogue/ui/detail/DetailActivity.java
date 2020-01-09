package piuwcreative.moviecatalogue.ui.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import piuwcreative.moviecatalogue.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "data_from_adapter";
    public static final String EXTRA_TYPE = "type_from_adapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }


}
