package piuwcreative.moviecatalogue.service.broadcast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import piuwcreative.moviecatalogue.R;
import piuwcreative.moviecatalogue.model.MovieModel;
import piuwcreative.moviecatalogue.ui.MainActivity;

public class NotificationReceiver extends BroadcastReceiver implements NotificationView.View {
    private static final String EXTRA_TYPE = "piuwcreative.moviecatalogue.broadcast.TYPE";
    public static final String TYPE_DAILY_REMINDER = "daily_reminder";
    public static final String TYPE_NEW_MOVIE = "new_movie_reminder";
    public static final int ID_DAILY = 201;
    public static final int ID_NEWS = 202;

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(EXTRA_TYPE);

        int id = type.equals(TYPE_DAILY_REMINDER) ? ID_DAILY : ID_NEWS;

        if (type.equals(TYPE_DAILY_REMINDER)) {
            showDailyReminderNotification(context, id);
            Log.i("cekking", "start daily notification");

        } else if (type.equals(TYPE_NEW_MOVIE)) {
            Log.i("cekking", "start-new movie");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String currentDate = dateFormat.format(date);

            NotificationPresenter presenter = new NotificationPresenter(context, id, this);
            presenter.requestMovies(currentDate);

            Log.i("cekking", "end-new movie");
        }
    }

    @Override
    public void setMovie(final Context context, final int id, final ArrayList<MovieModel> models) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int notifId = id;
                for (MovieModel model : models) {
                    showReleaseNotification(context, notifId, model);
                    notifId++;
                }
            }
        }).start();
    }

    public void setEnableNotificationService(Context context, String type, String time) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra(EXTRA_TYPE, type);

        Calendar calendar = Calendar.getInstance();
        String[] times = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
        calendar.set(Calendar.SECOND, 0);
        int requestCode = type.equals(TYPE_DAILY_REMINDER) ? 102 : 103;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void setDisableNotification(Context context, String type) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int requestCode = type.equals(TYPE_DAILY_REMINDER) ? ID_DAILY : ID_NEWS;
        Intent intent = new Intent(context, NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

    }

    private void showDailyReminderNotification(Context context, int id) {
        String channelId = "channel_2";
        String channelName = "daily reminder channel";
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_app)
                .setContentTitle(context.getString(R.string.notif_title))
                .setContentText(context.getString(R.string.notif_text))
                .setSound(sound)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(channelId);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();
        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }
    }

    private void showReleaseNotification(Context context, int id, MovieModel model) {

        String channelId = "channel_1";
        String channelName = "new movie channel";
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_movie)
                .setContentTitle(context.getString(R.string.news_title))
                .setContentText(model.getTitle())
                .setSound(sound)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(channelId);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();
        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }


    }
}
