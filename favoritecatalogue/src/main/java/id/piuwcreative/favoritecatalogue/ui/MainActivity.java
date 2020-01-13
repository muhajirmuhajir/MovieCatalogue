package id.piuwcreative.favoritecatalogue.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import id.piuwcreative.favoritecatalogue.R;
import id.piuwcreative.favoritecatalogue.adapter.FavoritePagerAdapter;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);

        FragmentManager fm = getSupportFragmentManager();
        FavoritePagerAdapter adapter = new FavoritePagerAdapter(fm, this);

        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(callback);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    TabLayout.OnTabSelectedListener callback = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


}
