package com.example.photoprintsandgifts.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.photoprintsandgifts.R;
import java.util.ArrayList;
import java.util.List;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.FrameViewHolder> {

    Context context;
    List<Integer> frameList;
    FrameAdapterListener listener;

    int row_selected = -1;

    public FrameAdapter(Context context, FrameAdapterListener listener) {
        this.context = context;
        this.frameList = getFrameList();
        this.listener = listener;

    }

    public List<Integer> getFrameList(){
        List<Integer> result = new ArrayList<>();

        result.add(R.drawable.frame_black);
        result.add(R.drawable.frame_blue);
        result.add(R.drawable.frame_green);
        result.add(R.drawable.frame_orange);
        result.add(R.drawable.frame_pink);
        result.add(R.drawable.frame_purple);
        result.add(R.drawable.frame_theia);
        result.add(R.drawable.frame_white);
        result.add(R.drawable.frame_yellow);

        return result;
    }

    @NonNull
    @Override
    public FrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.frame_card,parent, false);

        return new FrameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameViewHolder holder, int position) {
        if(row_selected == position){
          holder.checkPhoto.setVisibility(View.VISIBLE);
        }else {
            holder.checkPhoto.setVisibility(View.INVISIBLE);
        }
        holder.paperFrame.setImageResource(frameList.get(position));
    }

    @Override
    public int getItemCount() {
        return frameList.size();
    }

    public class FrameViewHolder extends RecyclerView.ViewHolder {
        ImageView checkPhoto, paperFrame;
        public FrameViewHolder(@NonNull View itemView) {
            super(itemView);
            checkPhoto = itemView.findViewById(R.id.iv_frame_card_check);
            paperFrame = itemView.findViewById(R.id.iv_png_frame);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                listener.onFrameSelected(frameList.get(getBindingAdapterPosition()));
                row_selected = getBindingAdapterPosition();
                notifyDataSetChanged();
                }
            });
        }
    }
    public interface FrameAdapterListener{

        void onFrameSelected(int frame);

    }
}