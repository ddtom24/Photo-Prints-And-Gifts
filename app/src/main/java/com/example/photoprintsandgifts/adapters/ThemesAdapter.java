package com.example.photoprintsandgifts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoprintsandgifts.FramesUpload;
import com.example.photoprintsandgifts.ImageRecycler;
import com.example.photoprintsandgifts.R;
import com.example.photoprintsandgifts.models.Theme;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ThemesHolder> {

    ArrayList<Theme> list;
    ThemesHolder.onThemesClicked listener;


    public ThemesAdapter(ArrayList<Theme> list, FramesUpload _listener){
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public ThemesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_preview_card, parent, false);

       ThemesHolder holder = new ThemesHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ThemesHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImagePath()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ThemesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onThemesClicked listener;
        private ImageView iv;
        private TextView tv;

        public ThemesHolder(@NonNull View itemView, onThemesClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_cv_theme_preview);
            tv = itemView.findViewById(R.id.tv_cv_theme_name);
            listener = _listener;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            listener.onThemesClick(getBindingAdapterPosition());
        }

        public interface onThemesClicked{

            public void onThemesClick(int pos);

        }
    }


}

