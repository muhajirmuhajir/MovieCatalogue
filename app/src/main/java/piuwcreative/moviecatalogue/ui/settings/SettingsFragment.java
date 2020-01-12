package piuwcreative.moviecatalogue.ui.settings;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import piuwcreative.moviecatalogue.R;

import static piuwcreative.moviecatalogue.utils.Const.KEY_DAILY_REMINDER;
import static piuwcreative.moviecatalogue.utils.Const.KEY_NEWS;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Fragment fragment;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragment = new SettingsPreferences();
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, fragment).commit();
        }

        Button changeLanguage = view.findViewById(R.id.btn_change_language);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
            }
        });

    }

    public static class SettingsPreferences extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        private SwitchPreference dailyReminder;
        private SwitchPreference newsReminder;

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.settings_preferences);

            init();
            setSummaries();
        }


        private void init() {
            dailyReminder = findPreference("reminder");
            newsReminder = findPreference("news");
        }

        private void setSummaries() {
            SharedPreferences sh = getPreferenceManager().getSharedPreferences();

            dailyReminder.setChecked(sh.getBoolean(KEY_DAILY_REMINDER, false));
            newsReminder.setChecked(sh.getBoolean(KEY_NEWS, false));
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(KEY_DAILY_REMINDER)) {
                dailyReminder.setChecked(sharedPreferences.getBoolean(KEY_DAILY_REMINDER, true));
            }
            if (key.equals(KEY_NEWS)) {
                newsReminder.setChecked(sharedPreferences.getBoolean(KEY_NEWS, false));
            }
        }
    }
}
