package dzo.com.barcodescanner;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vivek vsking on 1/19/2019.
 * vivekpcst.kumar@gmail.com
 */
public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
        private OnRecyclerItemSwipedListener itemSwipedListener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, OnRecyclerItemSwipedListener itemSwipedListener) {
        super(dragDirs, swipeDirs);
        this.itemSwipedListener=itemSwipedListener;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(viewHolder!=null){
            final View foregroundView=((DbItemAdapter.MyViewHolder)viewHolder).clForGround;
            getDefaultUIUtil().onSelected(foregroundView);
        }

    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final View forgroundView=((DbItemAdapter.MyViewHolder)viewHolder).clForGround;
        getDefaultUIUtil().onDrawOver(c,recyclerView,forgroundView,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        final View foregroundView=((DbItemAdapter.MyViewHolder)viewHolder).clForGround;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final View forgroundView=((DbItemAdapter.MyViewHolder)viewHolder).clForGround;
        getDefaultUIUtil().onDraw(c,recyclerView,forgroundView,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        itemSwipedListener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface OnRecyclerItemSwipedListener{
        void onSwiped(RecyclerView.ViewHolder viewHolder,int direction,int position);
    }
}
