package com.example.photoprintsandgifts.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoprintsandgifts.ImageRecycler;
import com.example.photoprintsandgifts.R;
import com.example.photoprintsandgifts.models.Theme;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    ArrayList<Theme> list;
    ImageHolder.onImageClicked listener;


    public ImageAdapter(ArrayList<Theme> list, ImageRecycler _listener){
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_preview_card, parent, false);

        ImageHolder holder = new ImageHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImagePath()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onImageClicked listener;
        private ImageView iv;
        private TextView tv;

        public ImageHolder(@NonNull View itemView, onImageClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_cv_theme_preview);
            tv = itemView.findViewById(R.id.tv_cv_theme_name);
            listener = _listener;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            listener.onImageClick(getBindingAdapterPosition());
        }

        public interface onImageClicked{

            public void onImageClick(int pos);

        }
    }


}

