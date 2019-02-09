package dzo.com.barcodescanner;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
/**
 * A simple {@link Fragment} subclass.
 */
public class SecondSlideShow extends Fragment{
    public SecondSlideShow() {
        // Required empty public constructor
    }
ImageView ivSecondSlideShow;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second_slide_show, container, false);
        ivSecondSlideShow=view.findViewById(R.id.ivSecondSlideShow);
//        ivSecondSlideShow.setImageResource(R.drawable.image);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("color", 000);
            try {
                ivSecondSlideShow.setBackgroundColor(Color.parseColor("#" + myInt));
            }catch(Exception e){
                ivSecondSlideShow.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }

        }
        return view;
    }


}
