package dzo.com.barcodescanner;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TTSService extends Service implements TextToSpeech.OnInitListener{

    private String str;
    private TextToSpeech mTts;
    private static final String TAG="TTSService";
    @Override

    public IBinder onBind(Intent arg0) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTts = new TextToSpeech(this,
                this  // OnInitListener
        );
        mTts.setSpeechRate(0.5f);
        Log.v(TAG, "oncreate_service");
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm ss a");
        String formattedDate = formatDate.format(new Date()).toString();
        String p[]=formattedDate.split(" ");

        str ="The time is "+p[0]+" and "+p[1]+" seconds "+p[2];
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mTts = new TextToSpeech(this,
                this  // OnInitListener
        );
        mTts.setSpeechRate(0.5f);
        Log.v(TAG, "oncreate_service");
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm ss a");
        String formattedDate = formatDate.format(new Date()).toString();

        String p[]=formattedDate.split(" ");

        str ="The time is "+p[0]+" and "+p[1]+" seconds "+p[2];
        sayHello(str);

        return START_STICKY;
    }
 /*   @Override
    public void onStart(Intent intent, int startId) {


        sayHello(str);

        Log.v(TAG, "onstart_service");
        super.onStart(intent, startId);
    }
*/
    @Override
    public void onInit(int status) {
        Log.v(TAG, "oninit");
        if (status == TextToSpeech.SUCCESS) {
            int result = mTts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.v(TAG, "Language is not available.");
            } else {

                sayHello(str);

            }
        } else {
            Log.v(TAG, "Could not initialize TextToSpeech.");
        }
    }
    private void sayHello(String str) {
        mTts.speak(str,
                TextToSpeech.QUEUE_FLUSH,
                null);
    }
}