package dzo.com.barcodescanner;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * Created by Vivek vsking on 1/22/2019.
 * vivekpcst.kumar@gmail.com
 */
public class MyNotificationService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    NotificationManager reminderNoti;
    String CHANNEL_ID="REMINDER_CHANNEL";
    public static final int NOTIFICATION_ID=1;
    public MyNotificationService(){
        super("MyNotificationService");

    }
    public MyNotificationService(String name) {
        super("MyNotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        sendNotification("Time Reminder !");
    }

    private void sendNotification(String msg) {

        reminderNoti= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentActivity=PendingIntent.getActivity(this,0,new Intent(this,Main.class),PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder noyifBuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Reminder").setSmallIcon(R.mipmap.ic_launcher).setContentText(msg).setAutoCancel(true);
        noyifBuilder.setContentIntent(contentActivity);

        reminderNoti.notify(NOTIFICATION_ID,noyifBuilder.build());
    }
}
