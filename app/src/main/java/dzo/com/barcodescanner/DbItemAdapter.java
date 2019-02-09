package dzo.com.barcodescanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vivek vsking on 1/19/2019.
 * vivekpcst.kumar@gmail.com
 */
public class DbItemAdapter extends RecyclerView.Adapter<DbItemAdapter.MyViewHolder> {

    Context context;
    String id[],name[],age[],Occupation[];
    List<GirlItemObject> girlItemObjectList;
    public DbItemAdapter(Context context,String id[],String name[],String []age,String []Occupation){
        this.context=context;
        this.id=id;
        this.age=age;
        this.Occupation=Occupation;
        this.name=name;
    }

    public DbItemAdapter(Context context, List<GirlItemObject> girlItemObjectList) {
        this.context=context;
        this.girlItemObjectList=girlItemObjectList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.db_item,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.tvGId.setText(id[position]);
//        holder.tvgOccupation.setText(Occupation[position]);
//        holder.tvgAge.setText(age[position]);
//        holder.tvgName.setText(name[position]);
        GirlItemObject item=girlItemObjectList.get(position);
        holder.tvGId.setText(item.getId());
        holder.tvgOccupation.setText(item.getOccu());
        holder.tvgAge.setText(item.getAge());
        holder.tvgName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return girlItemObjectList.size();
    }

    public void removeItem(int deletedIndex) {
        girlItemObjectList.remove(deletedIndex);
        notifyItemRemoved(deletedIndex);
    }
    public void reStoreItem(GirlItemObject item,int position){
        girlItemObjectList.add(position,item);
        notifyItemInserted(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvGId,tvgName,tvgAge,tvgOccupation,tvDelet;
        ConstraintLayout clForGround,clBackGround;
        ImageView ivDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDelete=itemView.findViewById(R.id.ivDelete);
            tvGId=itemView.findViewById(R.id.tvGId);
            tvgName=itemView.findViewById(R.id.tvDelete);
            tvgAge=itemView.findViewById(R.id.tvgAge);
            tvgOccupation=itemView.findViewById(R.id.tvgOccupation);
            clForGround=itemView.findViewById(R.id.clForground);
            clBackGround=itemView.findViewById(R.id.clBackground);
            tvDelet=itemView.findViewById(R.id.tvDelet);

        }
    }
}
