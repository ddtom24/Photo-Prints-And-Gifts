package com.example.photoprintsandgifts.adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.photoprintsandgifts.R;

import java.util.ArrayList;
import java.util.List;

public class StickersAdapter extends RecyclerView.Adapter<StickersAdapter.StickersViewHolder> {

   private Context context;
   private List<Integer> stickersList;
   private StickersAdapterListener listener;

    int row_selected = -1;

    public StickersAdapter(Context context, StickersAdapterListener listener) {
        this.context = context;
        this.stickersList = getStickersList();
        this.listener = listener;

    }

    public List<Integer> getStickersList(){
        List<Integer> stickers = new ArrayList<>();
        stickers.add(R.drawable.sticker_unicorn_theme1);
        stickers.add(R.drawable.sticker_unicorn_theme2);

        return stickers;
    }

    @NonNull
    @Override
    public StickersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.stickers_card,parent, false);

        return new StickersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StickersViewHolder holder, int position) {
        if(row_selected == position){
            holder.checkStickers.setVisibility(View.VISIBLE);
        }else {
            holder.checkStickers.setVisibility(View.INVISIBLE);
        }
        holder.stickers.setImageResource(stickersList.get(position));
    }

    @Override
    public int getItemCount() {
        return stickersList.size();
    }


    public class StickersViewHolder extends RecyclerView.ViewHolder {
        ImageView checkStickers, stickers;
        public StickersViewHolder(@NonNull View itemView) {
            super(itemView);
            checkStickers = itemView.findViewById(R.id.iv_stickers_card_check);
            stickers = itemView.findViewById(R.id.iv_png_stickers);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //noinspection deprecation
                    listener.onStickersSelected(stickersList.get(getAdapterPosition()));
                    //noinspection deprecation
                    row_selected = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
    public interface StickersAdapterListener{

        void onStickersSelected(int frame);


    }
}

/*

 */