package dzo.com.barcodescanner;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;


/**
 * Created by Vivek vsking on 1/22/2019.
 * vivekpcst.kumar@gmail.com
 */
public class MyService extends Service{

    private MediaPlayer mediaPlayer;
    TextToSpeech t1;
public MyService(){}

    @Override
    public void onCreate() {
        super.onCreate();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Date d = new Date();
        String speech = DateFormat.format("HH:MM a ", d.getTime()).toString();


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        t1.speak(speech, TextToSpeech.QUEUE_FLUSH, null);





//Start media player
//        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.start();
        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
    }

}
