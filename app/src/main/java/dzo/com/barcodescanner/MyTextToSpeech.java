package dzo.com.barcodescanner;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTextToSpeech extends Fragment {
    private PendingIntent pendingIntent;
    private static final int REQUEST_CODE = 133;


    public MyTextToSpeech() {
        // Required empty public constructor
    }

    EditText etSec,etMin;
    Button btSpeak;
    TextClock textClock;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_text_to_speech, container, false);
        etSec=view.findViewById(R.id.etSec);
        etMin=view.findViewById(R.id.etMin);
        btSpeak=view.findViewById(R.id.btSpeak);
        textClock=view.findViewById(R.id.tcTime);


        btSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curr_time=textClock.getText().toString();
                String speech="The time is "+curr_time;
//                mtts.speak(speech,TextToSpeech.QUEUE_FLUSH,null);
                Intent rmIntent = new Intent(getContext(), MyReceiver.class);
//                    getContext().sendBroadcast(rmIntent);
                pendingIntent = PendingIntent.getBroadcast(getContext(), REQUEST_CODE, rmIntent.setAction(curr_time),0);
                String minute=etMin.getText().toString();
                int min=0,sec=0;
                if(!minute.isEmpty()){
                     min= Integer.parseInt(minute);
                }
                String second=etSec.getText().toString();
                if(!second.isEmpty()){
                     sec=Integer.parseInt(second);
                }
            int interval=sec+min*60;

                //                String interval=etSec.getText().toString().trim();
//                if(!interval.equals("")&&!interval.equals("0")){
                if(interval>0){
                triggerAlarmManager(interval);
                }


            }
        });
        return view;
    }
    public void triggerAlarmManager(int alarmTriggerTime) {
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add alarmTriggerTime seconds to the calendar object
        cal.add(Calendar.SECOND, alarmTriggerTime);

        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);//set alarm manager with entered timer by converting into milliseconds

    }

    private int getTimeInterval(String getInterval) {
        int interval = Integer.parseInt(getInterval);//convert string interval into integer

            return interval;// * 60;//convert minute into seconds
    }

/*

    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS){
            int resut=mtts.setLanguage(Locale.US);
            if(resut==TextToSpeech.LANG_MISSING_DATA||resut==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(getContext(), "Missing data or lang not supported ! 404.", Toast.LENGTH_SHORT).show();
            }
        }else {
            // Initialization failed.
            Toast.makeText(getContext(), "Could not initialize TextToSpeech. ! 404.", Toast.LENGTH_SHORT).show();

            // May be its not installed so we prompt it to be installed
            Intent installIntent = new Intent();
            installIntent.setAction(
                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
        }

    }
*/

    @Override
    public void onDetach() {
        super.onDetach();
      /*  if (mtts != null) {
            mtts.stop();
            mtts.shutdown();
        }*/
    }
}
