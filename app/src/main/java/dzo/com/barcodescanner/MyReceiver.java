package dzo.com.barcodescanner;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;

/**
 * Created by Vivek vsking on 1/22/2019.
 * vivekpcst.kumar@gmail.com
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Reminder....", Toast.LENGTH_SHORT).show();

        //Stop sound service to play sound for alarm
        context.startService(new Intent(context, TTSService.class));

        //This will send a notification message and show notification in notification tray
        ComponentName comp = new ComponentName(context.getPackageName(), MyNotificationService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
    }
}
