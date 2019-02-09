package dzo.com.barcodescanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vivek vsking on 1/30/2019.
 * vivekpcst.kumar@gmail.com
 */
public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<HorizontalRecyclerAdapter.HorViewHolder> {
    Context context;
    ArrayList<HorItemModel> list;


    public HorizontalRecyclerAdapter(Context context, ArrayList<HorItemModel> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public HorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.horizontal_list_item,parent,false);

        return new HorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorViewHolder holder, int position) {
        HorItemModel
                itemModel=list.get(position);
                holder.tvHorItem.setText(itemModel.getTitle());
                Picasso.get()
                .load(itemModel.getImage())
                .error(R.drawable.image)
                .placeholder(R.drawable.image)
                .into(holder.ivHorItem);
//                holder.ivHorItem.setImageResource(R.drawable.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HorViewHolder extends RecyclerView.ViewHolder {
        View item;
        ImageView ivHorItem;
        TextView tvHorItem;
        public HorViewHolder(@NonNull View item) {
            super(item);
            this.item=item;
            ivHorItem=item.findViewById(R.id.ivHorItem);
            tvHorItem=item.findViewById(R.id.tvHorItem);

        }
    }
}
