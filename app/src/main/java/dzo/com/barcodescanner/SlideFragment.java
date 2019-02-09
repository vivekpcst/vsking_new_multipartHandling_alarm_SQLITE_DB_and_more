package dzo.com.barcodescanner;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideFragment extends Fragment implements FirstSlideShow.OnFragmentItemCLickListener {


    public SlideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_slide, container, false);
        Fragment fragment=new FirstSlideShow();
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        transaction.add(R.id.firstSlideContent,fragment).commit();

        return view;
    }
    @Override
    public void onFragmentClick(String color) {

    }

}
