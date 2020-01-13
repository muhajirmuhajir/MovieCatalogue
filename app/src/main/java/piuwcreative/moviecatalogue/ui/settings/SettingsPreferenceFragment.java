package piuwcreative.moviecatalogue.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.service.broadcast.NotificationReceiver;

import static piuwcreative.moviecatalogue.service.broadcast.NotificationReceiver.TYPE_DAILY_REMINDER;
import static piuwcreative.moviecatalogue.service.broadcast.NotificationReceiver.TYPE_NEW_MOVIE;

public class SettingsPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SwitchPreference dailyReminder;
    private SwitchPreference newsReminder;
    private NotificationReceiver receiver;
    private String DAILY;
    private String NEWS;


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

    private void setSummaries() {
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();

        dailyReminder.setChecked(sh.getBoolean(DAILY, false));
        newsReminder.setChecked(sh.getBoolean(NEWS, false));

    }

    private void init() {
        DAILY = getResources().getString(R.string.key_daily);
        NEWS = getResources().getString(R.string.key_news);

        dailyReminder = findPreference(DAILY);
        newsReminder = findPreference(NEWS);

        receiver = new NotificationReceiver();
        Log.i("cekking", "notification init done");
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(DAILY)) {
            boolean state = sharedPreferences.getBoolean(DAILY, false);
            dailyReminder.setChecked(state);

            if (state) {
                Log.i("cekking", "starting daily ");
                String time = "07:00";
                receiver.setEnableNotificationService(getContext(), TYPE_DAILY_REMINDER, time);
            } else {
                Log.i("cekking", "stopping daily ");
                receiver.setDisableNotification(getContext(), TYPE_DAILY_REMINDER);
            }

        }
        if (s.equals(NEWS)) {
            boolean state = sharedPreferences.getBoolean(NEWS, false);
            newsReminder.setChecked(state);
            if (state) {
                Log.i("cekking", "starting news ");
                String time = "08:00";
                receiver.setEnableNotificationService(getContext(), TYPE_NEW_MOVIE, time);
            } else {
                Log.i("cekking", "stopping daily ");
                receiver.setDisableNotification(getContext(), TYPE_NEW_MOVIE);
            }
        }

    }


}
