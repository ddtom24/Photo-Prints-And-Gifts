package com.example.photoprintsandgifts.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photoprintsandgifts.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UploadPhotosAdapter extends RecyclerView.Adapter<UploadPhotosAdapter.ViewHolder> {


    private ArrayList<Uri> uriArrayList;
    private Context context;
    UpdatedImageCount updatedImageCount;



    public UploadPhotosAdapter(ArrayList<Uri> uriArrayList, Context context, UpdatedImageCount updatedImageCount) {
        this.uriArrayList = uriArrayList;
        this.context = context;
        this.updatedImageCount = updatedImageCount;


    }


    @NonNull
    @Override
    public UploadPhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_card, parent, false);

        return new ViewHolder(view, updatedImageCount);
    }


    public void onBindViewHolder(@NonNull UploadPhotosAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // holder.image.setImageURI(uriArrayList.get(position));
         Glide.with(context).load(uriArrayList.get(position)).into(holder.image);
        //implementation for deleting photo(s)
         holder.delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
               uriArrayList.remove(uriArrayList.get(position));
               notifyItemRemoved(position);
               notifyItemRangeChanged(position, getItemCount());
               updatedImageCount.clicked(uriArrayList.size());

             }
         });
    }



    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image, delete;
        UpdatedImageCount updatedImageCount;

        public ViewHolder(@NonNull View itemView, UpdatedImageCount updatedImageCount) {
            super(itemView);
            this.updatedImageCount = updatedImageCount;

            image = itemView.findViewById(R.id.iv_photo_card);
            delete = itemView.findViewById(R.id.iv_delete_photo);

        }
    }

    public interface UpdatedImageCount{
        void clicked(int getSize);
    }
}
