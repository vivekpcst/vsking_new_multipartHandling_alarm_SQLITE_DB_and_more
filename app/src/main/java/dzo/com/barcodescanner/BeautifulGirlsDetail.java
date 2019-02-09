package dzo.com.barcodescanner;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeautifulGirlsDetail extends Fragment implements View.OnClickListener {


    public BeautifulGirlsDetail() {
        // Required empty public constructor
    }

// TextView tvIdLevel,tvName,tvAge,tvOccupation;
   private EditText etId,etName,etAge,etOccup;
    private Button btSaveData,btDelData;
    private OnDbSavedListener dbSavedListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_beautiful_girls_detail, container, false);
//        tvIdLevel=view.findViewById(R.id.tvIdLevel);
//        tvName=view.findViewById(R.id.tvName);
//        tvAge=view.findViewById(R.id.tvAge);
//        tvOccupation=view.findViewById(R.id.tvOccupation);

        etId=view.findViewById(R.id.etId);
        etName=view.findViewById(R.id.etName);
        etAge=view.findViewById(R.id.etAge);
        etOccup=view.findViewById(R.id.etOccup);

        btSaveData=view.findViewById(R.id.btSaveData);
        btDelData=view.findViewById(R.id.btDelData);
        btSaveData.setOnClickListener(this);
        btDelData.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnDbSavedListener){
            dbSavedListener= (OnDbSavedListener) context;
        }else {
            throw new RuntimeException(context.toString()+" must implement OnDbSavedListener ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dbSavedListener=null;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSaveData: {
                String id, name, age, occup;
                id = etId.getText().toString();
                name = etName.getText().toString();
                age = etAge.getText().toString();
                occup = etOccup.getText().toString();
                if (id.isEmpty() || name.isEmpty() || age.isEmpty() || occup.isEmpty()) {
                    Toast.makeText(getActivity(), "Fill all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    dbSavedListener.onDbSaved(id, name, age, occup);
                    etId.setText("");
                    etAge.setText("");
                    etName.setText("");
                    etOccup.setText("");
                    Toast.makeText(getActivity(), "Data saved successfully !", Toast.LENGTH_LONG).show();

                }
                FragmentTransaction ft = null;
                if (getFragmentManager() != null) {
                    ft = getFragmentManager().beginTransaction();
                    ft.detach(this).attach(this).commit();
                }
                break;
            }
            case R.id.btDelData:{
                String id=etId.getText().toString();
                if(id.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Id to delete !", Toast.LENGTH_SHORT).show();
                }else{
                    dbSavedListener.onItemDelete(id);
                    etId.setText("");
                }
                FragmentTransaction ft = null;
                if (getFragmentManager() != null) {
                    ft = getFragmentManager().beginTransaction();
                    ft.detach(this).attach(this).commit();
                }
                break;
            }
        }
    }
    public interface OnDbSavedListener{
       public void onDbSaved(String id,String name,String age,String occupation);
       public void onItemDelete(String id);
    }
}
