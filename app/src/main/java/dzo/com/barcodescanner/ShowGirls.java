package dzo.com.barcodescanner;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowGirls extends Fragment implements RecyclerItemTouchHelper.OnRecyclerItemSwipedListener {


    public ShowGirls() {
        // Required empty public constructor
    }
    public OnItemSweepDeleteListener deleteListener;
    public interface OnItemSweepDeleteListener{
        void onDeleteSwip(String BeautifulGirlsDetail);
    }
    String id,name,age,occup;
    RecyclerView recyclerView;
   List<GirlItemObject> girlItemObjectList;
    DbItemAdapter dbItemAdapter;
    GirlItemObject itemObject;
    ConstraintLayout girlsLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_show_girls, container, false);
        recyclerView=view.findViewById(R.id.rvGirlList);
        girlsLayout=view.findViewById(R.id.girlsLayout);
        itemObject=new GirlItemObject();
        girlItemObjectList=new ArrayList<GirlItemObject>();
//        getAllData();
        dbItemAdapter=new DbItemAdapter(getContext(),girlItemObjectList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
        getAllData();
        return view;

    }

    private void getAllData() {
        MyDatabaseHelper db=new MyDatabaseHelper(getContext());
        SQLiteDatabase database=db.getWritableDatabase();
        String SELECT_QUERY="SELECT * FROM "+ GlobalContact.DbContact.TABLE_NAME;
        Cursor cursor=database.rawQuery(SELECT_QUERY,null);
//        id=new String[cursor.getCount()];
//        name=new String[cursor.getCount()];
//        age=new String[cursor.getCount()];
//        occup=new String[cursor.getCount()];
        if(cursor.moveToFirst()){
            do {
                id/*[cursor.getPosition()]*/=cursor.getString(0);
                name/*[cursor.getPosition()]*/=cursor.getString(1);
                age/*[cursor.getPosition()]*/=cursor.getString(2);
                occup/*[cursor.getPosition()]*/=cursor.getString(3);
              girlItemObjectList.add(new GirlItemObject(id,name,age,occup));
//                girlItemObjectList.add(itemObject);
            }while (cursor.moveToNext());
            cursor.close();
        }

//        DbItemAdapter dbItemAdapter=new DbItemAdapter(getContext(),id,name,age,occup);
        DbItemAdapter dbItemAdapter=new DbItemAdapter(getContext(),girlItemObjectList);
        recyclerView.setAdapter(dbItemAdapter);
        dbItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnItemSweepDeleteListener){
            deleteListener= (OnItemSweepDeleteListener) context;
        }else {
            throw new RuntimeException(context.toString()+ "must implement delete listener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        deleteListener=null;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof DbItemAdapter.MyViewHolder){
            String name=girlItemObjectList.get(viewHolder.getAdapterPosition()).getName();
            String id=girlItemObjectList.get((viewHolder.getAdapterPosition())).getId();
            final GirlItemObject deletedItem=girlItemObjectList.get(viewHolder.getAdapterPosition());
            final int deletedIndex=viewHolder.getAdapterPosition();

            dbItemAdapter.removeItem(deletedIndex);
            MyDatabaseHelper dbHelper=new MyDatabaseHelper(getContext());
            dbHelper.deleteRow(id);
            Toast.makeText(getContext(), name+" is deleted from list !", Toast.LENGTH_SHORT).show();
            deleteListener.onDeleteSwip("BeatifulGirlsDetail");
        }
    }
}
