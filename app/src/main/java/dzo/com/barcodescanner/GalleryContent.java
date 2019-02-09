package dzo.com.barcodescanner;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryContent extends Fragment {


    public GalleryContent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gallery_content, container, false);
        VideoView videoView=view.findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        Uri uri= Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.welcome);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.start();

        return view;
    }

}
