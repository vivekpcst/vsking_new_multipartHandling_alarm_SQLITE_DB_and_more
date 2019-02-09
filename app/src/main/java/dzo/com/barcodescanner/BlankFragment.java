package dzo.com.barcodescanner;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }
    private ArrayList<HorItemModel> list= new ArrayList<>();
    private RecyclerView rvHorizontal;
    private HorizontalRecyclerAdapter horAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        rvHorizontal=view.findViewById(R.id.rvHorizontal);

        rvHorizontal.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL));
        horAdapter = new HorizontalRecyclerAdapter(getActivity(), list);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvHorizontal.setLayoutManager(horizontalLayoutManager);
        rvHorizontal.setAdapter(horAdapter);
        setList();
        autoScroll();

        return view;

    }
    private void autoScroll(){
        final int speedScroll = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count == horAdapter.getItemCount())
                    count =0;
                if(count < horAdapter.getItemCount()){
                    rvHorizontal.smoothScrollToPosition(count++);
                    handler.postDelayed(this,speedScroll);
                }
            }
        };
        handler.postDelayed(runnable,speedScroll);
    }
    private void setList() {
        String []a={"https://english.cdn.zeenews.com/sites/default/files/2019/01/31/757912-disha.jpg"
                ,"https://images.indianexpress.com/2017/11/disha-patani-759.jpg"
                ,"https://c-6rtwjumjzx7877x24nrl-x78-rx78s-htrx2efpfrfnejix2esjy.g01.msn.com/g00/3_c-6bbb.rx78s.htr_/c-6RTWJUMJZX77x24myyux78x3ax2fx2fnrl-x78-rx78s-htr.fpfrfneji.sjyx2fyjsfsyx2ffrux2fjsynydnix2fGGSr2sC.nrlx3fmx3d155x26bx3d244x26rx3d1x26vx3d15x26tx3dkx26qx3dkx26cx3d050x26dx3d854x26n65h.rfwpx3dnrflj_$/$/$/$/$"
                ,"https://images.mid-day.com/images/2018/dec/Disha-Patani-a_d.jpg"
                ,"http://filmyfocus.com/wp-content/uploads/2016/10/Disha-Patani-had-a-nightmarish-experience.jpg"
                ,"http://media.vogue.in/wp-content/uploads/2018/06/disha-patani-beauty-tips-makeup-hairstyles-vogue-india1-400x400.jpg"
                ,"http://i.ndtvimg.com/i/2017-01/disha-patani_640x480_71485779957.jpg"
                ,"https://akm-img-a-in.tosshub.com/indiatoday/images/story/201901/disha-patani-trolled.jpeg"
                ,"https://images.news18.com/ibnlive/uploads/2018/11/disha-patani.jpg"
                ,"https://static.toiimg.com/photo/65593651.cms"
        };
        for (int i=0;i<10;i++){
            String image=a[i];
            String title="Disha's pic_"+(i+1);
            list.add(new HorItemModel(image,title));
        }
        horAdapter.notifyDataSetChanged();
    }

}
